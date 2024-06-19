package com.springboot.rideService.service;

import com.google.gson.Gson;
import com.springboot.rideService.dto.DriverStatusUpdateDto;
import com.springboot.rideService.dto.RideRequestDto;
import com.springboot.rideService.model.Ride;
import com.springboot.rideService.repository.RideRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RideService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private RideRepository rideRepository;


    public void sendRideRequest(RideRequestDto rideRequestDto) {
        kafkaTemplate.send("ride-requests", rideRequestDto);
    }

    public void processDriverStatusUpdate(String message) {
        log.info("[processDriverStatusUpdate] :::::: message-{}",message);

        DriverStatusUpdateDto driverStatusUpdateDto = new Gson().fromJson(message,DriverStatusUpdateDto.class);

        Ride ride = new Ride();
        ride.setDriverId(driverStatusUpdateDto.getDriverId());
        ride.setStatus("Allocated");
        rideRepository.save(ride);
    }
}
