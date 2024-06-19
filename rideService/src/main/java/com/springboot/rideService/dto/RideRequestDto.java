package com.springboot.rideService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RideRequestDto {
    private String userId;
    private GeoJsonPoint pickupLocation;
    private GeoJsonPoint dropoffLocation;

}

