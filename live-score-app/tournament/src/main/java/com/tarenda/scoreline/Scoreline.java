package com.tarenda.scoreline;

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
//@Table(name = "scoreline")
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
    @ManyToOne
    @JoinColumn(name="tournamentId")
    private Tournament tournament;
    private String teamAName;
    private String teamBName;
    private Integer teamAScore;
    private Integer teamBScore;
    private LocalDate matchDate;
}
