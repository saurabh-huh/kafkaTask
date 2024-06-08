package com.springboot.notificationService.controller;

import com.springboot.notificationService.dto.DriverStatusUpdateDto;
import com.springboot.notificationService.dto.RideRequestDto;
import com.springboot.notificationService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "ride-requests", groupId = "ride-sharing-group")
    public void consumeRideRequest(RideRequestDto rideRequestDto) {
        notificationService.sendRideConfirmation(rideRequestDto);
    }

    @KafkaListener(topics = "driver-status-updates", groupId = "ride-sharing-group")
    public void consumeDriverStatusUpdate(DriverStatusUpdateDto driverStatusUpdateDto) {
        notificationService.sendDriverStatusUpdate(driverStatusUpdateDto);
    }
}
