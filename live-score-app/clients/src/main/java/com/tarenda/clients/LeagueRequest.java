package com.tarenda.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeagueRequest {
    private String teamName;
    private Integer gamesPlayed;
    private Integer won;
    private Integer draw;
    private Integer lost;
    private Integer goalDifference;
    private Integer points;
}
