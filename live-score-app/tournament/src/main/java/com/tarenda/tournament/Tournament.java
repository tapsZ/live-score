package com.tarenda.tournament;

import com.tarenda.tournament.scoreline.Scoreline;
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
    private Long id;
    private LocalDate createdAt;

    @OneToMany(targetEntity=Scoreline.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "scoreline_fk", referencedColumnName = "id")
    private List<Scoreline> scorelines;
}
