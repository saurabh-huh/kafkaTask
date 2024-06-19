package com.springboot.driverService;

import com.springboot.driverService.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DriverServiceApplication {
	@Autowired
	DriverRepository driverRepository;
	public static void main(String[] args) {
		SpringApplication.run(DriverServiceApplication.class, args);
	}

}
