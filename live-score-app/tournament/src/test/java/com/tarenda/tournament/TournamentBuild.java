package com.tarenda.tournament;

import com.tarenda.clients.ScorelineDTO;
import com.tarenda.clients.TournamentRequestDTO;
import com.tarenda.tournament.scoreline.Scoreline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TournamentBuild {
    protected static List<Tournament> getTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        //Tournament 1
        Tournament tournament1 = Tournament.builder()
                .scorelines(getScorelineList().subList(0,2).stream().map(TournamentBuild::transformToScoreline).toList())
                .build();

        //Tournament 2
        Tournament tournament2 = Tournament.builder()
                .scorelines(getScorelineList().subList(2,4).stream().map(TournamentBuild::transformToScoreline).toList())
                .build();

        tournaments.add(tournament1);
        tournaments.add(tournament2);
        return tournaments;
    }

    private static Scoreline transformToScoreline(ScorelineDTO scorelineDTO) {
        return Scoreline.builder()
                .teamAName(scorelineDTO.getTeamAName())
                .teamAScore(scorelineDTO.getTeamAScore())
                .teamBName(scorelineDTO.getTeamBName())
                .teamBScore(scorelineDTO.getTeamBScore())
                .build();
    }

    private static List<ScorelineDTO> getScorelineList(){
        List<ScorelineDTO> scorelineList = new ArrayList<>();
        ScorelineDTO scoreline1 = getScoreline("Lions", 3, "Snakes", 3);
        ScorelineDTO scoreline2 = getScoreline("Tarantulas", 1, "FC Awesome", 0);
        ScorelineDTO scoreline3 = getScoreline("Lions", 1, "FC Awesome", 1);
        ScorelineDTO scoreline4 = getScoreline("Tarantulas", 3, "Snakes", 1);
        ScorelineDTO scoreline5 = getScoreline("Lions", 4, "Grouches", 0);
        scorelineList.add(scoreline1);
        scorelineList.add(scoreline2);
        scorelineList.add(scoreline3);
        scorelineList.add(scoreline4);
        scorelineList.add(scoreline5);
        return scorelineList;
    }

public static Tournament transformToTournament(TournamentRequestDTO tournamentRequestDTO){
        return Tournament.builder()
                .title(tournamentRequestDTO.getTitle())
                .scorelines(tournamentRequestDTO.getScorelines().stream().map(TournamentBuild::transformToScoreline).toList())
                .build();
    }

    public static TournamentRequestDTO getTournamentRequestDTO(){
        return TournamentRequestDTO.builder()
                .scorelines(getScorelineList())
                .build();
    }

    private static ScorelineDTO getScoreline(String teamAName, Integer teamAScore, String teamBName, Integer teamBScore) {
        return ScorelineDTO.builder()
                .teamAName(teamAName)
                .teamAScore(teamAScore)
                .teamBName(teamBName)
                .teamBScore(teamBScore)
                .build();
    }
}
