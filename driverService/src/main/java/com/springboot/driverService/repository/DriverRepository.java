package com.springboot.driverService.repository;

import com.springboot.driverService.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DriverRepository extends MongoRepository<Driver, UUID> {

    List<Driver> findByStatus(String available);
}
