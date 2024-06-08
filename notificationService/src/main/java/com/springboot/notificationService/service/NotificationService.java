package com.springboot.notificationService.service;


import com.springboot.notificationService.dto.DriverStatusUpdateDto;
import com.springboot.notificationService.dto.RideRequestDto;
import com.springboot.notificationService.model.Notification;
import com.springboot.notificationService.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

   @Autowired
    private NotificationRepository notificationRepository;

    public void sendRideConfirmation(RideRequestDto rideRequestDto) {
        Notification notification = new Notification();
        notification.setMessage("Ride request confirmed for user: " + rideRequestDto.getUserId());
        notificationRepository.save(notification);
    }

    public void sendDriverStatusUpdate(DriverStatusUpdateDto driverStatusUpdateDto) {
        Notification notification = new Notification();
        notification.setMessage("Driver status updated for driver: " + driverStatusUpdateDto.getDriverId() + " to " + driverStatusUpdateDto.getStatus());
        notificationRepository.save(notification);
    }
}
