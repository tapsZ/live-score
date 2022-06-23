package com.tarenda.league;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataMongoTest
public class LeagueRepositoryTest {

    @Autowired
    private LeagueRepository underTest;

    @AfterEach    
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void IfCanRecordLeague() {
        //given
        List<League> leagues = LeagueBuild.getLeagues();
        League league1 = leagues.get(0);
        underTest.save(league1);
        //when
        League expected = null;
        if (underTest.findTopByOrderByIdDesc().isPresent()) {
            expected = underTest.findTopByOrderByIdDesc().get();
        }
        //then
        assertThat(league1).isEqualTo(expected);
    }

    @Test
    void IfLeagueDoesNotExist() {
        // given not all leagues are saved
        List<League> leagues = LeagueBuild.getLeagues();
        League league1 = leagues.get(0);
        League league2 = leagues.get(1);
        underTest.save(league1);

        //when
        League expected = null;
        if (underTest.findTopByOrderByIdDesc().isPresent()) {
            expected = underTest.findTopByOrderByIdDesc().get();
        }
        //then
        assertThat(league1).isEqualTo(expected);
        assertThat(league2).isNotEqualTo(expected);
    }

    @Test
    void IfFindTheLatestLeague() throws InterruptedException {
        // given both leagues are saved
        List<League> leagues = LeagueBuild.getLeagues();
        League league1 = leagues.get(0);
        League league2 = leagues.get(1);
        underTest.save(league1);
        TimeUnit.SECONDS.sleep(2);
        underTest.save(league2);
        //when
        League actual = null;
        if (underTest.findTopByOrderByIdDesc().isPresent()) {
            actual = underTest.findTopByOrderByIdDesc().get();
        }
        assertThat(actual).isEqualTo(league2);

    }
}
