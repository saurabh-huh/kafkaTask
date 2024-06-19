package com.springboot.notificationService.service;


import com.google.gson.Gson;
import com.springboot.notificationService.dto.DriverStatusUpdateDto;
import com.springboot.notificationService.dto.RideRequestDto;
import com.springboot.notificationService.model.Notification;
import com.springboot.notificationService.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

   @Autowired
    private NotificationRepository notificationRepository;

    public void sendRideConfirmation(String message) {

        log.info("[sendRideConfirmation] ::::: message -> {}",message);
        RideRequestDto rideRequestDto = new Gson().fromJson(message,RideRequestDto.class);

        Notification notification = new Notification();
        notification.setMessage("Ride request confirmed for user: " + rideRequestDto.getUserId());
        notificationRepository.save(notification);
    }

    public void sendDriverStatusUpdate(String message) {
        log.info("[sendDriverStatusUpdate] ::: message->{}",message);

        DriverStatusUpdateDto driverStatusUpdateDto = new Gson().fromJson(message,DriverStatusUpdateDto.class);

        Notification notification = new Notification();
        notification.setMessage("Driver status updated for driver: " + driverStatusUpdateDto.getDriverId() + " to " + driverStatusUpdateDto.getStatus());
        notificationRepository.save(notification);
    }
}
