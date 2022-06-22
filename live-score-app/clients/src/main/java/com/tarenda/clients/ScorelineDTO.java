package com.tarenda.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScorelineDTO {
    @NotBlank
    private String teamAName;
    @NotBlank
    private String teamBName;
    @NotBlank
    private Integer teamAScore;
    @NotBlank
    private Integer teamBScore;

    private Integer teamAPoints;
    private Integer teamBPoints;
}
