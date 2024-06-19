package com.springboot.rideService.controller;

import com.springboot.rideService.dto.DriverStatusUpdateDto;
import com.springboot.rideService.dto.RideRequestDto;
import com.springboot.rideService.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rides")
public class RideController {

    private final RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping
    public ResponseEntity<String> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        rideService.sendRideRequest(rideRequestDto);
        return ResponseEntity.ok("Ride request sent");
    }

    @KafkaListener(topics = "driver-status-updates", groupId = "ride-sharing-group")
    public void consumeDriverStatusUpdate(String message) {
        rideService.processDriverStatusUpdate(message);
    }

}
