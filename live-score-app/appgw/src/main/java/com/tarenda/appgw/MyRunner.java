package com.tarenda.appgw;

import com.tarenda.appgw.formLeague.TournamentLeagueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    private final TournamentLeagueService tournamentLeagueService;

    public MyRunner(TournamentLeagueService tournamentLeagueService) {
        this.tournamentLeagueService = tournamentLeagueService;
    }

    @Override
    public void run(String... args) throws Exception {
//        tournamentLeagueService.formLeague();
    }
}
