package com.tarenda.tournament;

import com.tarenda.scoreline.Scoreline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "tournament")
public class Tournament {
    @Id
    @SequenceGenerator(
            name = "tournament_id_sequence",
            sequenceName = "tournament_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tournament_id_sequence"
    )
    private Long tournamentId;
    @OneToMany(targetEntity=Scoreline.class, mappedBy="tournament", fetch=FetchType.EAGER)
    private List<Scoreline> scorelines;
    private LocalDate createdAt;
}
