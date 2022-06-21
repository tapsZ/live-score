package com.tarenda.tournament;

import com.tarenda.scoreline.Scoreline;

import java.util.List;
import java.util.Set;

public record TournamentRequest(
        Set<Scoreline> scorelines
) {
}
