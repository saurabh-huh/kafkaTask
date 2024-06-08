package com.springboot.notificationService.repository;

import com.springboot.notificationService.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NotificationRepository extends MongoRepository<Notification, UUID> {
}
