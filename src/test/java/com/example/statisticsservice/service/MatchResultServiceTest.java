package com.example.statisticsservice.service;

import com.example.statisticsservice.controller.dto.ResultDto;
import com.example.statisticsservice.model.Team;
import com.example.statisticsservice.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchResultServiceTest {

    private static final Long WINNING_POINTS = 3L;
    private static final Long LOOSING_POINTS = 0L;
    private static final Long DRAFT_POINTS = 1L;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private MatchResultService matchResultService;

    @Test
    void shouldSaveWinningResultWhenHomeTeamWin() {

        //given
        ResultDto resultDto = new ResultDto("Home", "Away", 2L, 1L);

        Team homeTeam = Team.builder()
                .name("Home")
                .playedEvents(0L)
                .gainedPoints(0L)
                .goalsScored(0L)
                .goalsConceded(0L)
                .build();

        Team awayTeam = Team.builder()
                .name("Away")
                .playedEvents(0L)
                .gainedPoints(0L)
                .goalsScored(0L)
                .goalsConceded(0L)
                .build();

        //when
        when(teamRepository.getByName("Home")).thenReturn(Optional.of(homeTeam));
        when(teamRepository.getByName("Away")).thenReturn(Optional.of(awayTeam));

        matchResultService.saveMathResults(resultDto);

        //then
        verify(teamRepository, times(2)).save(any(Team.class));
        verify(teamRepository, times(1)).getByName("Home");
        verify(teamRepository, times(1)).getByName("Away");

        assertThat(homeTeam.getPlayedEvents(), equalTo(1L));
        assertThat(homeTeam.getGainedPoints(), equalTo(WINNING_POINTS));
        assertThat(homeTeam.getGoalsScored(), equalTo(2L));
        assertThat(homeTeam.getGoalsConceded(), equalTo(1L));

        assertThat(awayTeam.getPlayedEvents(), equalTo(1L));
        assertThat(awayTeam.getGainedPoints(), equalTo(LOOSING_POINTS));
        assertThat(awayTeam.getGoalsScored(), equalTo(1L));
        assertThat(awayTeam.getGoalsConceded(), equalTo(2L));
    }

    @Test
    void shouldSaveWinningResultWhenAwayTeamWin() {

        //given
        ResultDto resultDto = new ResultDto("Home", "Away", 0L, 3L);

        Team homeTeam = Team.builder()
                .name("Home")
                .playedEvents(0L)
                .gainedPoints(0L)
                .goalsScored(0L)
                .goalsConceded(0L)
                .build();


        Team awayTeam = Team.builder()
                .name("Away")
                .playedEvents(0L)
                .gainedPoints(0L)
                .goalsScored(0L)
                .goalsConceded(0L)
                .build();

        //when
        when(teamRepository.getByName("Home")).thenReturn(Optional.of(homeTeam));
        when(teamRepository.getByName("Away")).thenReturn(Optional.of(awayTeam));

        matchResultService.saveMathResults(resultDto);

        //then
        verify(teamRepository, times(2)).save(any(Team.class));
        verify(teamRepository, times(1)).getByName("Home");
        verify(teamRepository, times(1)).getByName("Away");

        assertThat(homeTeam.getPlayedEvents(), equalTo(1L));
        assertThat(homeTeam.getGainedPoints(), equalTo(LOOSING_POINTS));
        assertThat(homeTeam.getGoalsScored(), equalTo(0L));
        assertThat(homeTeam.getGoalsConceded(), equalTo(3L));

        assertThat(awayTeam.getPlayedEvents(), equalTo(1L));
        assertThat(awayTeam.getGainedPoints(), equalTo(WINNING_POINTS));
        assertThat(awayTeam.getGoalsScored(), equalTo(3L));
        assertThat(awayTeam.getGoalsConceded(), equalTo(0L));
    }

    @Test
    void shouldSaveWinningResultWhenDrawOccur() {

        //given
        ResultDto resultDto = new ResultDto("Home", "Away", 2L, 2L);

        Team homeTeam = Team.builder()
                .name("Home")
                .playedEvents(0L)
                .gainedPoints(0L)
                .goalsScored(0L)
                .goalsConceded(0L)
                .build();

        Team awayTeam = Team.builder()
                .name("Away")
                .playedEvents(0L)
                .gainedPoints(0L)
                .goalsScored(0L)
                .goalsConceded(0L)
                .build();

        //when
        when(teamRepository.getByName("Home")).thenReturn(Optional.of(homeTeam));
        when(teamRepository.getByName("Away")).thenReturn(Optional.of(awayTeam));

        matchResultService.saveMathResults(resultDto);

        //then
        verify(teamRepository, times(2)).save(any(Team.class));
        verify(teamRepository, times(1)).getByName("Home");
        verify(teamRepository, times(1)).getByName("Away");

        assertThat(homeTeam.getPlayedEvents(), equalTo(1L));
        assertThat(homeTeam.getGainedPoints(), equalTo(DRAFT_POINTS));
        assertThat(homeTeam.getGoalsScored(), equalTo(2L));
        assertThat(homeTeam.getGoalsConceded(), equalTo(2L));

        assertThat(awayTeam.getPlayedEvents(), equalTo(1L));
        assertThat(awayTeam.getGainedPoints(), equalTo(DRAFT_POINTS));
        assertThat(awayTeam.getGoalsScored(), equalTo(2L));
        assertThat(awayTeam.getGoalsConceded(), equalTo(2L));
    }
}