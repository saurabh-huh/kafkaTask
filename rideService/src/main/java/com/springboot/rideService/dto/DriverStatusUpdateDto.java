package com.springboot.rideService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverStatusUpdateDto {
    private String driverId;
    private String status;

}

