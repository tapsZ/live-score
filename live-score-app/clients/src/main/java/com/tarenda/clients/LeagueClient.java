package com.tarenda.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("league")
public interface LeagueClient {

    @GetMapping("api/v1/league/retrieve")
    LeagueResponseDTO retrieveLeague();

    @PostMapping("api/v1/league/create")
    LeagueResponseDTO createLeague(@RequestBody TournamentRequestDTO tournamentRequest);
}
