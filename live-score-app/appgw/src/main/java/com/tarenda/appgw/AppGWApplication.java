package com.tarenda.appgw;

import com.tarenda.appgw.formLeague.TournamentLeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.tarenda.clients")
public class AppGWApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGWApplication.class, args);
    }

    @Autowired
    private TournamentLeagueService tournamentLeagueService;

    @Bean
    CommandLineRunner runner() {
        return args -> tournamentLeagueService.formLeague();
    }
}
