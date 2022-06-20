package com.tarenda.league;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team {
    private String name;
    private Integer gamesPlayed;
    private Integer won;
    private Integer draw;
    private Integer lost;
    private Integer goalDifference;
    private Integer points;
}
