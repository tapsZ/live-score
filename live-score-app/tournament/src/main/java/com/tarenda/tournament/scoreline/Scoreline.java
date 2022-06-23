package com.tarenda.tournament.scoreline;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Setter(AccessLevel.NONE)
    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
