package com.springboot.rideService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "ride")
public class Ride {
    @Id
    private String id = UUID.randomUUID().toString();
    private String driverId;
    private String status;

}
