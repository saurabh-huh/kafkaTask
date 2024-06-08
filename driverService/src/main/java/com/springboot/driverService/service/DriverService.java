package com.springboot.driverService.service;

import com.springboot.driverService.dto.DriverStatusUpdateDto;
import com.springboot.driverService.dto.RideRequestDto;
import com.springboot.driverService.model.Driver;
import com.springboot.driverService.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DriverService {

    @Autowired
    private KafkaTemplate<String, DriverStatusUpdateDto> kafkaTemplate;
    @Autowired
    private DriverRepository driverRepository;

    public void sendDriverStatusUpdate(DriverStatusUpdateDto driverStatusUpdateDto) {
        kafkaTemplate.send("driver-status-updates", driverStatusUpdateDto);
    }

    public void processRideRequest(RideRequestDto rideRequestDto) {
        // Logic to notify drivers of ride requests
        Optional<Driver> availableDriver = driverRepository.findByStatus("available").stream().findFirst();

        if (availableDriver.isPresent()) {
            Driver driver = availableDriver.get();
            driver.setStatus("on-trip");
            driverRepository.save(driver);

            // Send driver status update to Kafka
            DriverStatusUpdateDto driverStatusUpdateDto = new DriverStatusUpdateDto();
            driverStatusUpdateDto.setDriverId(driver.getId().toString());
            driverStatusUpdateDto.setStatus("on-trip");

            sendDriverStatusUpdate(driverStatusUpdateDto);

            // Optionally: log the allocation
            System.out.println("Driver " + driver.getId() + " assigned to user " + rideRequestDto.getUserId());
        } else {
            // Handle the case where no drivers are available
            System.out.println("No available drivers for user " + rideRequestDto.getUserId());
        }
    }
}