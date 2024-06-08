package com.springboot.rideService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverStatusUpdateDto {
    private UUID driverId;
    private String status;

}

