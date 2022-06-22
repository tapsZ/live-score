package com.tarenda.tournament.scoreline;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public record ScorelineService(ScorelineRepository scorelineRepository) {

//    public void recordScoreline(ScorelineRequest scorelineRequest) {
//        Scoreline scoreline = Scoreline.builder()
//                .teamAName(scorelineRequest.teamAName())
//                .teamAScore(scorelineRequest.teamAScore())
//                .teamBName(scorelineRequest.teamBName())
//                .teamBScore(scorelineRequest.teamBScore())
//                .matchDate(LocalDate.now())
//                .build();
//        //store scoreline in db
//        scorelineRepository.save(scoreline);
//
//        TODO Calculate points
//
//    }
}
