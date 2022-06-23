package com.tarenda.appgw.formLeague;

import com.tarenda.clients.LeagueClient;
import com.tarenda.clients.TournamentClient;
import com.tarenda.clients.TournamentRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TournamentLeagueServiceTest {

    private TournamentLeagueService underTest;
    @Mock
    private TournamentClient tournamentClient;
    @Mock
    private LeagueClient leagueClient;

    @BeforeEach
    void setUp(){
        underTest = new TournamentLeagueService(tournamentClient, leagueClient);
        ReflectionTestUtils.setField(underTest, "address", "src/test/resources/demo.txt");
    }

    @Test
    void doesProcessTheTournamentCorrectly() throws IOException {
        //given
        TournamentRequestDTO tournamentRequestDTO = TournamentLeagueBuild.getTournamentRequestDTO();
        given(tournamentClient.processTournament(tournamentRequestDTO))
                .willReturn(TournamentLeagueBuild.getLeagueResponseDTO());
        //when
        underTest.formLeague();
        //then
        verify(tournamentClient).processTournament(tournamentRequestDTO);
        verify(leagueClient, never()).retrieveLeague();
    }

    @Test
    void willThrowWhenFileLocationIsNotCorrect(){
        //given bad data
        ReflectionTestUtils.setField(underTest, "address", "src/test/resources/demo-ghost.txt");
        //when
        //then
        assertThatThrownBy(()-> underTest.formLeague())
                .isInstanceOf(IOException.class)
                .hasMessageContaining("File not found:") ;
    }

    @Test
    void willThrowWhenFileHasInValidScorelines() {
        //given
        ReflectionTestUtils.setField(underTest, "address", "src/test/resources/demo-bad-data.txt");
        //when
        //then
        assertThatThrownBy(()-> underTest.formLeague())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("An invalid scoreline was found:") ;
    }

    @Test
    void willThrowWhenFileIsBlank() {
        //given
        ReflectionTestUtils.setField(underTest, "address", "src/test/resources/demo-blank.txt");
        //when
        // then
        assertThatThrownBy(()-> underTest.formLeague())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No scoreline was found in the file:") ;

    }

}