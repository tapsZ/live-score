package com.tarenda.tournament;

import com.tarenda.clients.LeagueClient;
import com.tarenda.clients.TournamentRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    private TournamentService underTest;
    @Mock
    private TournamentRepository tournamentRepository;
    @Mock
    private LeagueClient leagueClient;

    @BeforeEach
    void setUp() {
        underTest = new TournamentService(tournamentRepository, leagueClient);
    }


    @Test
    void canRecordTournament() {
        //given
        TournamentRequestDTO tournamentRequestDTO = TournamentBuild.getTournamentRequestDTO();
        Tournament tournament = TournamentBuild.transformToTournament(tournamentRequestDTO);
        //when
        underTest.saveTournamentAndCreateLeague(tournamentRequestDTO);
        // then
        verify(tournamentRepository).save(tournament);
        verify(leagueClient).createLeague(tournamentRequestDTO);
    }

    @Test
    void isTheRightTournamentSaved() {
        //given
        TournamentRequestDTO tournamentRequestDTO = TournamentBuild.getTournamentRequestDTO();
        Tournament tournament = TournamentBuild.transformToTournament(tournamentRequestDTO);
        //when
        underTest.saveTournamentAndCreateLeague(tournamentRequestDTO);
        // then

        //Capture
        ArgumentCaptor<Tournament> tournamentArgumentCaptor = ArgumentCaptor.forClass(Tournament.class);
        verify(tournamentRepository).save(tournamentArgumentCaptor.capture());

        //assert
        Tournament capturedTournament = tournamentArgumentCaptor.getValue();
        assertThat(capturedTournament).isEqualTo(tournament);
    }

    @Test
    void isTheRightTournamentUsedToCreateLeague() {
        //given
        TournamentRequestDTO tournamentRequestDTO = TournamentBuild.getTournamentRequestDTO();

        //when
        underTest.saveTournamentAndCreateLeague(tournamentRequestDTO);
        // then

        //Capture
        ArgumentCaptor<TournamentRequestDTO> tournamentRequestDTOArgumentCaptor = ArgumentCaptor.forClass(TournamentRequestDTO.class);
        verify(leagueClient).createLeague(tournamentRequestDTOArgumentCaptor.capture());

        //assert
        TournamentRequestDTO capturedTournamentRequestDTO = tournamentRequestDTOArgumentCaptor.getValue();
        assertThat(capturedTournamentRequestDTO).isEqualTo(tournamentRequestDTO);
    }
}