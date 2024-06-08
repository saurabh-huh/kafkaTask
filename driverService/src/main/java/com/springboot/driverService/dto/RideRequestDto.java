package com.springboot.driverService.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@RequiredArgsConstructor
@Data
public class RideRequestDto {
    private UUID userId;
    private String pickupLocation;
    private String dropoffLocation;


}

