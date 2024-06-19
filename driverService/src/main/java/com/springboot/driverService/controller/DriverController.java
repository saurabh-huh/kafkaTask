package com.springboot.driverService.controller;

import com.springboot.driverService.dto.DriverStatusUpdateDto;
import com.springboot.driverService.dto.RideRequestDto;
import com.springboot.driverService.model.Driver;
import com.springboot.driverService.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver){
        return new ResponseEntity<>(driverService.addDriver(driver), HttpStatus.CREATED);
    }

    @GetMapping("/{lon}/{lat}/{dist}")
    public ResponseEntity<List<Driver>> findMemberByLocationNear(@PathVariable(value = "lon") double lon,@PathVariable(value = "lat") double lat,@PathVariable(value = "dist") double dist){
        return new ResponseEntity<>(driverService.findMemberByLocationNear(lon,lat,dist),HttpStatus.OK);
    }


    @PostMapping("/status")
    public ResponseEntity<String> updateDriverStatus(@RequestBody DriverStatusUpdateDto driverStatusUpdateDto) {
        driverService.sendDriverStatusUpdate(driverStatusUpdateDto);
        return ResponseEntity.ok("Driver status update sent");
    }

    @KafkaListener(topics = "ride-requests", groupId = "ride-sharing-group")
    public void consumeRideRequest(String message) {
        driverService.processRideRequest(message);
    }
}

