package com.springboot.rideService.repository;

import com.springboot.rideService.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RideRepository extends MongoRepository<Ride, UUID> {
}
