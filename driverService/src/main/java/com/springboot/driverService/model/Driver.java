package com.springboot.driverService.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Document(collection = "driver")
public class Driver {
    @Id
    private UUID id;
    private String status;


}
