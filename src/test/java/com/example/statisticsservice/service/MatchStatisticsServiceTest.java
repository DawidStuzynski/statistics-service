package com.example.statisticsservice.service;

import com.example.statisticsservice.model.Team;
import com.example.statisticsservice.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchStatisticsServiceTest {

    @InjectMocks
    private MatchStatisticsService matchStatisticsService;

    @Mock
    private TeamRepository teamRepository;

    @Test
    void shouldReturnTeamStatisticsInProperFormat() {

        //given
        String teamName = "Barcelona";
        Team team = Team.builder()
                .name(teamName)
                .playedEvents(3L)
                .gainedPoints(7L)
                .goalsScored(5L)
                .goalsConceded(3L)
                .build();

        //when
        when(teamRepository.getByName(teamName)).thenReturn(Optional.of(team));
        String statistics = matchStatisticsService.getTeamStatistics(teamName);

        //then
        assertEquals("Barcelona 3 7 5 3", statistics);
    }

    @Test
    void shouldThrowExceptionWhenTeamDoesNotExist() {

        //given
        String teamName = "Barcelona";

        //when
        when(teamRepository.getByName(teamName)).thenReturn(Optional.empty());

        //then
        assertThrows(RuntimeException.class, () -> matchStatisticsService.getTeamStatistics(teamName));
    }
}