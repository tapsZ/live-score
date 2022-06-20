package com.tarenda.scoreline;

public record ScorelineRecordRequest(
        String teamAName,
        String teamBName,
        Integer teamAScore,
        Integer teamBScore
) {
}
