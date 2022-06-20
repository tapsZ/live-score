package com.tarenda.league;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@Document
public class League {
    @Id
    private String id;
//    @Indexed(unique = true)
    private String title;
    private List<Team> teams;
    private LocalDateTime createdAt;
}
