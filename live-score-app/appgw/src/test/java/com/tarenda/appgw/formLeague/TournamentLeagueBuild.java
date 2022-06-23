package com.tarenda.appgw.formLeague;

import com.tarenda.clients.LeagueResponseDTO;
import com.tarenda.clients.ScorelineDTO;
import com.tarenda.clients.TournamentRequestDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TournamentLeagueBuild {

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

    public static TournamentRequestDTO getTournamentRequestDTO(){
        return TournamentRequestDTO.builder()
                .scorelines(getScorelineList())
                .build();
    }

    public static LeagueResponseDTO getLeagueResponseDTO(){
        return LeagueResponseDTO.builder()
                .teams(getTeams())
                .build();
    }

    private static Map<String, Integer> getTeams() {
        HashMap<String, Integer> teams= new HashMap<>();
        teams.put("Tarantulas", 6);
        teams.put("Lions", 4);
        teams.put("FC Awesome", 1);
        teams.put("Snakes", 1);
        teams.put("Grouches", 0);
        return teams;
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
