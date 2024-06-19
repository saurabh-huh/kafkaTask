package com.springboot.notificationService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {
    private String userId;
    private GeoJsonPoint pickupLocation;
    private GeoJsonPoint dropoffLocation;

}

