package com.springboot.driverService.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@RequiredArgsConstructor
@Document(collection = "driver")
public class Driver {
    @Id
    private String id;
    private String firstName;
    private String email;
    private String status;
    private GeoJsonPoint location;
}
