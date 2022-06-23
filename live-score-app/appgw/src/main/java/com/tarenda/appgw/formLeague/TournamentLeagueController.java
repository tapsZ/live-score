package com.tarenda.appgw.formLeague;

import com.tarenda.clients.TournamentRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api/v1/tournament/league")
public record TournamentLeagueController(TournamentLeagueService tournamentLeagueService) {
    @GetMapping
    public void createALeague() throws Exception {
         tournamentLeagueService.formLeague();
    }

}
