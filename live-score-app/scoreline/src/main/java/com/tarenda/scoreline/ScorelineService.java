package com.tarenda.scoreline;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public record ScorelineService(ScorelineRepository scorelineRepository) {

    public void recordScoreline(ScorelineRecordRequest scorelineRecordRequest) {
        Scoreline scoreline = Scoreline.builder()
                .teamAName(scorelineRecordRequest.teamAName())
                .teamAScore(scorelineRecordRequest.teamAScore())
                .teamBName(scorelineRecordRequest.teamBName())
                .teamBScore(scorelineRecordRequest.teamBScore())
                .matchDate(LocalDate.now())
                .build();
        //store scoreline in db
        scorelineRepository.save(scoreline);
    }
}
