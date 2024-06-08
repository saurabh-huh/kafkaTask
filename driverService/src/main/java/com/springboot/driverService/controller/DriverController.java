package com.springboot.driverService.controller;

import com.springboot.driverService.dto.DriverStatusUpdateDto;
import com.springboot.driverService.dto.RideRequestDto;
import com.springboot.driverService.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;


    @PostMapping("/status")
    public ResponseEntity<String> updateDriverStatus(@RequestBody DriverStatusUpdateDto driverStatusUpdateDto) {
        driverService.sendDriverStatusUpdate(driverStatusUpdateDto);
        return ResponseEntity.ok("Driver status update sent");
    }

    @KafkaListener(topics = "ride-requests", groupId = "ride-sharing-group")
    public void consumeRideRequest(RideRequestDto rideRequestDto) {
        driverService.processRideRequest(rideRequestDto);
    }
}

