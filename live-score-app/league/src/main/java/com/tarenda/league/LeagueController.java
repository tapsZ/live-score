package com.tarenda.league;

import com.tarenda.clients.LeagueRequest;
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
    public void createLeague(@RequestBody LeagueRequest leagueRequest){
         leagueService.createLeague(leagueRequest);
    }
}
