package com.springboot.driverService.repository;

import com.springboot.driverService.model.Driver;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface DriverRepository extends MongoRepository<Driver, UUID> {

    List<Driver> findByStatus(String available);

    List<Driver> findByLocationNear (Point point, Distance distance);
}
