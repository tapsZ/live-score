package com.tarenda.tournament;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TournamentRepositoryTest {

    @Autowired
    private TournamentRepository repoUnderTest;


    @AfterEach
    void tearDown(){
        repoUnderTest.deleteAll();
    }

    @Test
    void IfCanRecordTournament() {
        //given
        List<Tournament> tournaments = TournamentBuild.getTournaments();
        Tournament tournament1 = tournaments.get(0);
        repoUnderTest.save(tournament1);
        //when
        Tournament expected = null;
        if (repoUnderTest.findTopByOrderByIdDesc().isPresent()) {
            expected = repoUnderTest.findTopByOrderByIdDesc().get();
        }
        //then
        assertThat(tournament1).isEqualTo(expected);
    }

    @Test
    void IfTournamentDoesNotExist() {
        // given not all tournaments are saved
        List<Tournament> tournaments = TournamentBuild.getTournaments();
        Tournament tournament1 = tournaments.get(0);
        Tournament tournament2 = tournaments.get(1);
        repoUnderTest.save(tournament1);

        //when
        Tournament expected = null;
        if (repoUnderTest.findTopByOrderByIdDesc().isPresent()) {
            expected = repoUnderTest.findTopByOrderByIdDesc().get();
        }
        //then
        assertThat(tournament1).isEqualTo(expected);
        assertThat(tournament2).isNotEqualTo(expected);
    }

    @Test
    void IfFindTheLatestTournament() throws InterruptedException {
        // given both tournaments are saved
        List<Tournament> tournaments = TournamentBuild.getTournaments();
        Tournament tournament1 = tournaments.get(0);
        Tournament tournament2 = tournaments.get(1);
        repoUnderTest.save(tournament1);
        TimeUnit.SECONDS.sleep(2);
        repoUnderTest.save(tournament2);
        //when
        Tournament actual = null;
        if (repoUnderTest.findTopByOrderByIdDesc().isPresent()) {
            actual = repoUnderTest.findTopByOrderByIdDesc().get();
        }
        assertThat(actual).isEqualTo(tournament2);

    }

}