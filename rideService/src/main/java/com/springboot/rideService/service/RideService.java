package com.springboot.rideService.service;

import com.springboot.rideService.dto.DriverStatusUpdateDto;
import com.springboot.rideService.dto.RideRequestDto;
import com.springboot.rideService.model.Ride;
import com.springboot.rideService.repository.RideRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class RideService {

    @Autowired
    private KafkaTemplate<String, RideRequestDto> kafkaTemplate;

    @Autowired
    private RideRepository rideRepository;


    public void sendRideRequest(RideRequestDto rideRequestDto) {
        kafkaTemplate.send("ride-requests", rideRequestDto);
    }

    public void processDriverStatusUpdate(DriverStatusUpdateDto driverStatusUpdateDto) {
        Ride ride = new Ride();
        ride.setDriverId(driverStatusUpdateDto.getDriverId());
        ride.setStatus("Allocated");
        rideRepository.save(ride);
    }
}
