package com.springboot.driverService.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.UUID;


@RequiredArgsConstructor
@Data
public class RideRequestDto {
    private String userId;
    private GeoJsonPoint pickupLocation;
    private GeoJsonPoint dropoffLocation;
}

