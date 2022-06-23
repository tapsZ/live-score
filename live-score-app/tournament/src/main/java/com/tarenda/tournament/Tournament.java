package com.tarenda.tournament;

import com.tarenda.tournament.scoreline.Scoreline;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    private String title;
    @OneToMany(targetEntity=Scoreline.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "scoreline_fk", referencedColumnName = "id")
    private List<Scoreline> scorelines;

    @Column(name = "CreatedAt", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date createdAt;
}
