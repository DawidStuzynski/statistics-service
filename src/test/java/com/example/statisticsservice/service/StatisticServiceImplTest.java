package com.example.statisticsservice.service;

import com.example.statisticsservice.controller.dto.MessageDto;
import com.example.statisticsservice.controller.dto.MessageType;
import com.example.statisticsservice.controller.dto.ResultDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {
    @InjectMocks
    private StatisticServiceImpl statisticService;
    @Mock
    private MatchResultServiceImpl matchResultService;
    @Mock
    private MatchStatisticsServiceImpl matchStatisticsService;

    @Test
    void fetchStatistics_returnsCorrectStatistics() {
        //given
        ResultDto resultDto = new ResultDto("Barcelona", "Real Madrid", 2L, 1L);
        MessageDto messageDto = new MessageDto(MessageType.RESULT, resultDto);
        String homeTeamStatistics = "Barcelona 1 3 2 1";
        String awayTeamStatistics = "Real Madrid 1 0 1 2";

        //when
        when(matchStatisticsService.getTeamStatistics("Barcelona")).thenReturn(homeTeamStatistics);
        when(matchStatisticsService.getTeamStatistics("Real Madrid")).thenReturn(awayTeamStatistics);

        String statistics = statisticService.fetchStatistics(messageDto);

        //then
        assertEquals(homeTeamStatistics + "\n" + awayTeamStatistics, statistics);
        verify(matchResultService).saveMathResults(resultDto);
    }
}