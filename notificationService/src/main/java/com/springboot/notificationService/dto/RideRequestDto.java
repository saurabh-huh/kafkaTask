package com.springboot.notificationService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {
    private UUID userId;
    private String pickupLocation;
    private String dropoffLocation;

}

