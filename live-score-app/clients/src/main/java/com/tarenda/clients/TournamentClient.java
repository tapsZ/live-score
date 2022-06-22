package com.tarenda.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("tournament")
public interface TournamentClient {
    @PostMapping("api/v1/tournament/record")
    LeagueResponseDTO recordScoreline( TournamentRequestDTO tournamentRequest);
}
