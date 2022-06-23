package com.tarenda.league;


import com.tarenda.clients.LeagueResponseDTO;
import com.tarenda.clients.TournamentRequestDTO;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LeagueServiceTest {

    private LeagueService underTest;
    @Mock
    private LeagueRepository leagueRepository;

    @BeforeEach
    void setUp(){
        underTest = new LeagueService(leagueRepository);
    }

    @Test
    void doesCreateLeague(){
        //given
        TournamentRequestDTO tournamentRequest = LeagueBuild.getTournamentRequestDTO();
        League league = LeagueBuild.getLeague();
        //when
        LeagueResponseDTO leagueResponseDTO = underTest.createLeague(tournamentRequest);
        //then
        verify(leagueRepository).insert(league);
        assertThat(leagueResponseDTO).isEqualTo(LeagueBuild.getLeagueResponseDTO());
    }
}
