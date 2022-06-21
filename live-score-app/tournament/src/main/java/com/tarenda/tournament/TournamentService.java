package com.tarenda.tournament;

import com.tarenda.clients.LeagueClient;
import com.tarenda.clients.LeagueRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final LeagueClient leagueClient;

    public void recordTournament(TournamentRequest tournamentRequest) {
        Tournament tournament = Tournament.builder()
                .createdAt(LocalDate.now())
//                .scorelines(tournamentRequest.scorelines())
                .build();
        tournamentRepository.save(tournament);
        LeagueRequest leagueRequest = new LeagueRequest();
        leagueClient.createLeague(leagueRequest);
    }
}
