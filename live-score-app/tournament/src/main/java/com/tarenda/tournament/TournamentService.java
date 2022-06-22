package com.tarenda.tournament;

import com.tarenda.clients.LeagueClient;
import com.tarenda.clients.LeagueResponseDTO;
import com.tarenda.clients.TournamentRequestDTO;
import com.tarenda.tournament.scoreline.Scoreline;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public record TournamentService(TournamentRepository tournamentRepository, LeagueClient leagueClient) {
    public LeagueResponseDTO recordTournament(TournamentRequestDTO tournamentRequest) {
        Tournament tournament = transformToTournament(tournamentRequest);
        tournamentRepository.save(tournament);
       return leagueClient.createLeague(tournamentRequest);
    }

    private Tournament transformToTournament(TournamentRequestDTO tournamentRequest) {
        List<Scoreline> scorelineList = new ArrayList<>();
        tournamentRequest.getScorelines().forEach(s-> scorelineList.add(Scoreline.builder()
                .matchDate(LocalDate.now())
                .teamAName(s.getTeamAName())
                .teamAScore(s.getTeamAScore())
                .teamBName(s.getTeamBName())
                .teamBScore(s.getTeamBScore())
                .build()));
        return Tournament.builder()
                .createdAt(LocalDate.now())
                .scorelines(scorelineList)
                .build();
    }
}
