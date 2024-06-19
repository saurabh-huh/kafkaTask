package com.springboot.driverService.service;

import com.google.gson.Gson;
import com.mongodb.client.model.geojson.Position;
import com.springboot.driverService.dto.DriverStatusUpdateDto;
import com.springboot.driverService.dto.RideRequestDto;
import com.springboot.driverService.model.Driver;
import com.springboot.driverService.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Service
public class DriverService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(KafkaTemplate<String, Object> kafkaTemplate, DriverRepository driverRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.driverRepository = driverRepository;
    }

    public void sendDriverStatusUpdate(DriverStatusUpdateDto driverStatusUpdateDto) {
        kafkaTemplate.send("driver-status-updates", driverStatusUpdateDto);
    }

    public void processRideRequest(String message) {
        // Logic to notify drivers of ride requests
        List<Driver> availableDrivers = driverRepository.findByStatus("available").stream().toList();
        RideRequestDto rideRequestDto = new Gson().fromJson(message,RideRequestDto.class);

        double longitude1 = rideRequestDto.getPickupLocation().getCoordinates().get(0);
        double latitude1 = rideRequestDto.getPickupLocation().getCoordinates().get(1);

        double maxDist = 10;
        Driver availableDriver = new Driver();
        for(Driver driver : availableDrivers){
            double longitude = driver.getLocation().getCoordinates().get(0);
            double latitude = driver.getLocation().getCoordinates().get(1);

            double distance = Math.sqrt((longitude-longitude1)*(longitude-longitude1) + (latitude-latitude1)*(latitude-latitude1));
            if(maxDist > distance ){
                maxDist = distance;
                availableDriver = driver;
            }
        }

        if (!availableDrivers.isEmpty() && maxDist <= 10) {//availableDriver.isPresent()
            Driver driver = availableDriver;
            driver.setStatus("on-trip");
            driverRepository.save(driver);

            // Send driver status update to Kafka
            DriverStatusUpdateDto driverStatusUpdateDto = new DriverStatusUpdateDto();
            driverStatusUpdateDto.setDriverId(driver.getId().toString());
            driverStatusUpdateDto.setStatus("on-trip");

            sendDriverStatusUpdate(driverStatusUpdateDto);

            log.info("Driver  -{} assigned to user -{} ",driver.getId(), message);
        } else {
            // Handle the case where no drivers are available
            log.info("No available drivers for user -{}" ,message);
        }
    }

    public List<Driver> findMemberByLocationNear(double longitude,double latitude,double distanceInKm){

        Point centralPoint = new Point(longitude,latitude);
        Distance maxDistance = new Distance(distanceInKm, Metrics.KILOMETERS);
        log.info("[findMemberByLocationNear]::::: fetched list!");
        return driverRepository.findByLocationNear(centralPoint,maxDistance);
    }

    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}