package com.tarenda.tournament;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.tarenda.clients"
)
public class TournamentApplication {
    public static void main(String[] args) {
        SpringApplication.run(TournamentApplication.class, args);
    }
}
