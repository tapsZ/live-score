package com.tarenda.league;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository extends MongoRepository<League, String> {
    Optional<League> findTopByOrderByCreatedAtDesc();
}
