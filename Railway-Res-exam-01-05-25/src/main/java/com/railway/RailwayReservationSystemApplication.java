package com.railway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.railway.*"})
@EntityScan("com.railway.model")
@EnableJpaRepositories("com.railway.repository")
public class RailwayReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailwayReservationSystemApplication.class, args);
    }
}