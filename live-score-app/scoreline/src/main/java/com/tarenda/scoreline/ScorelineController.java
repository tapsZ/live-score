package com.tarenda.scoreline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/scoreline")
public record ScorelineController(ScorelineService scorelineService) {

    @PostMapping
    public void recordScoreline(@RequestBody ScorelineRecordRequest scorelineRecordRequest){
        log.info("new score line recording{}", scorelineRecordRequest);
        scorelineService.recordScoreline(scorelineRecordRequest);
    }
}
