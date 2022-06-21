package com.tarenda.league;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.tarenda.clients"
)
public class LeagueApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeagueApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(LeagueService leagueService){
        return args -> {
            List<Team> teams = new ArrayList<>();

            Team team1 = Team.builder()
                    .name("ZUPCO")
                    .gamesPlayed(3)
                    .won(2)
                    .draw(1)
                    .lost(0)
                    .goalDifference(4)
                    .points(7)
                    .build();

            Team team2 = Team.builder()
                    .name("ZESA")
                    .gamesPlayed(2)
                    .won(1)
                    .draw(0)
                    .lost(1)
                    .goalDifference(-1)
                    .points(3)
                    .build();
            teams.add(team1);
            teams.add(team2);

            League league =  League.builder()
                    .createdAt(LocalDateTime.now())
                    .title("PSLLL")
                    .teams(teams)
                    .build();

            leagueService.saveLeague(league);

            LeagueResponse leagueResponse = leagueService.retrieveLeague();
            System.out.println(leagueResponse);
        };
    }
}
