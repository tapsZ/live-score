package com.tarenda.tournament.scoreline;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScorelineRepository extends JpaRepository<Scoreline, Long> {
}
