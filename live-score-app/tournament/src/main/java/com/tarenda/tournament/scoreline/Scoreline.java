package com.tarenda.tournament.scoreline;

import com.tarenda.tournament.Tournament;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Scoreline {
    @Id
    @SequenceGenerator(
            name = "scoreline_id_sequence",
            sequenceName = "scoreline_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "scoreline_id_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String teamAName;
    @Column(nullable = false)
    private String teamBName;
    @Column(nullable = false)
    private Integer teamAScore;
    @Column(nullable = false)
    private Integer teamBScore;
    @Column(nullable = false)
    private LocalDate matchDate;
}
