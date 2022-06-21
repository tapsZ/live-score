package com.tarenda.league;

import com.tarenda.clients.LeagueRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record LeagueService(LeagueRepository leagueRepository) {

    public void saveLeague(League league){
        leagueRepository.insert(league);
    }

    public LeagueResponse retrieveLeague() {
        Optional<League> league = leagueRepository.findTopByOrderByCreatedAtDesc();
        return LeagueResponse.builder()
                .league(league.get())
                .build();
    }

    public void createLeague(LeagueRequest leagueRequest) {
        League league =  League.builder().build();
        leagueRepository.insert(league);
    }
}
