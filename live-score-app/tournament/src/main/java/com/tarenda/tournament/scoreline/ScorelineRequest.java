package com.tarenda.tournament.scoreline;

public record ScorelineRequest(
        String teamAName,
        String teamBName,
        Integer teamAScore,
        Integer teamBScore
) {
}