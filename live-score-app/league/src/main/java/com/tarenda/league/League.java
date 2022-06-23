package com.tarenda.league;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@Document
public class League {
    @Id
    private String id;
    private Map<String, Integer> teams;
    private LocalDateTime createdAt;
}
