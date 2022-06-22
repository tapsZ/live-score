package com.tarenda.league;

import com.tarenda.clients.LeagueRequestDTO;
import com.tarenda.clients.TournamentRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/league")
public record LeagueController(LeagueService leagueService) {
    @GetMapping("/retrieve")
    public LeagueResponse retrieveLeague(){
        return leagueService.retrieveLeague();
    }

    @PostMapping("/create")
    public void createLeague(@RequestBody TournamentRequestDTO tournamentRequest){
         leagueService.createLeague(tournamentRequest);
    }
}
