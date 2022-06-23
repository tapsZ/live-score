package com.tarenda.tournament;

import com.tarenda.clients.LeagueClient;
import com.tarenda.clients.LeagueResponseDTO;
import com.tarenda.clients.TournamentRequestDTO;
import com.tarenda.tournament.scoreline.Scoreline;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record TournamentService(TournamentRepository tournamentRepository, LeagueClient leagueClient) {
    public LeagueResponseDTO saveTournamentAndCreateLeague(TournamentRequestDTO tournamentRequest) {
        Tournament tournament = transformToTournament(tournamentRequest);
        tournamentRepository.save(tournament);
       return leagueClient.createLeague(tournamentRequest);
    }

    private Tournament transformToTournament(TournamentRequestDTO tournamentRequest) {
        List<Scoreline> scorelineList = new ArrayList<>();
        tournamentRequest.getScorelines().forEach(s-> scorelineList.add(Scoreline.builder()
                .teamAName(s.getTeamAName())
                .teamAScore(s.getTeamAScore())
                .teamBName(s.getTeamBName())
                .teamBScore(s.getTeamBScore())
                .build()));
        return Tournament.builder()
                .scorelines(scorelineList)
                .build();
    }
}
