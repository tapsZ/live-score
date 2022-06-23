package com.tarenda.appgw;

import com.tarenda.appgw.formLeague.TournamentLeagueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Slf4j
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.tarenda.clients")
public class AppGWApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGWApplication.class, args);
    }
}
