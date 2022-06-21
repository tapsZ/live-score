package com.tarenda.tournament;

import com.tarenda.scoreline.ScorelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/tournament")
public record TournamentController(TournamentService tournamentService) {

    @PostMapping
    public void recordScoreline(@RequestBody TournamentRequest tournamentRequest){
        log.info("new score line recording{}", tournamentRequest);
        tournamentService.recordTournament(tournamentRequest);
    }
}
