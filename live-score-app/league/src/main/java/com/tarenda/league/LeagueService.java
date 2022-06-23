package com.tarenda.league;

import com.tarenda.clients.LeagueResponseDTO;
import com.tarenda.clients.ScorelineDTO;
import com.tarenda.clients.TournamentRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public record LeagueService(LeagueRepository leagueRepository) {
    private static final boolean DESC = false;

    public LeagueResponseDTO createLeague(TournamentRequestDTO tournamentRequest) {
        LeagueResponseDTO leagueResponseDTO = calculateLeague(tournamentRequest);
        League league = transformToLeague(leagueResponseDTO.getTeams());
        leagueRepository.insert(league);
        return leagueResponseDTO;
    }

    public LeagueResponseDTO retrieveLeague() {
        Optional<League> league = leagueRepository.findTopByOrderByIDDesc();
        return league.map(value -> LeagueResponseDTO.builder()
                .teams(value.getTeams())
                .build()).orElse(null);
    }

    private LeagueResponseDTO calculateLeague(TournamentRequestDTO tournamentRequest) {
        HashMap<String, Integer> league = new HashMap<>();
        for (ScorelineDTO scoreline : tournamentRequest.getScorelines()) {
            //Assign Points
            if (scoreline.getTeamAScore() > scoreline.getTeamBScore()) {
                scoreline.setTeamAPoints(Points.WIN.point);
                scoreline.setTeamBPoints(Points.LOSS.point);
            } else if (scoreline.getTeamAScore() < scoreline.getTeamBScore()) {
                scoreline.setTeamAPoints(Points.LOSS.point);
                scoreline.setTeamBPoints(Points.WIN.point);
            } else {
                scoreline.setTeamAPoints(Points.DRAW.point);
                scoreline.setTeamBPoints(Points.DRAW.point);
            }

            //Aggregate Points for teams that played numerous times
            if (league.containsKey(scoreline.getTeamAName())) {
                league.replace(scoreline.getTeamAName(), league.get(scoreline.getTeamAName()) + scoreline.getTeamAPoints());
            } else {
                league.put(scoreline.getTeamAName(), scoreline.getTeamAPoints());
            }

            if (league.containsKey(scoreline.getTeamBName())) {
                league.replace(scoreline.getTeamBName(), league.get(scoreline.getTeamBName()) + scoreline.getTeamBPoints());
            } else {
                league.put(scoreline.getTeamBName(), scoreline.getTeamBPoints());
            }
        }

        Map<String, Integer> sortedMapDesc = sortByValue(league);
        return LeagueResponseDTO.builder().teams(sortedMapDesc).build();
    }

    private League transformToLeague(Map<String, Integer> teams) {
        return League.builder()
                .teams(teams)
                .build();
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortedMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> LeagueService.DESC ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }
}
