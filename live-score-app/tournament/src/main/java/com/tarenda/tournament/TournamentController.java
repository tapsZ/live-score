package com.tarenda.tournament;

import com.tarenda.clients.LeagueResponseDTO;
import com.tarenda.clients.TournamentRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/tournament")
public record TournamentController(TournamentService tournamentService) {

    @PostMapping("/record")
    public LeagueResponseDTO recordScoreline(@Valid @RequestBody TournamentRequestDTO tournamentRequest){
        log.info("new score line recording{}", tournamentRequest);
        return tournamentService.recordTournament(tournamentRequest);
    }
}
