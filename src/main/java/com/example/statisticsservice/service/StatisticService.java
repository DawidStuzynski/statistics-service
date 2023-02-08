package com.example.statisticsservice.service;

import com.example.statisticsservice.controller.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final MatchResultService matchResultService;
    private final MatchStatisticsService matchStatisticsService;

    public String fetchStatistics(ResultDto resultDto) {
        matchResultService.saveMathResults(resultDto);

        String firstResult = matchStatisticsService.getTeamStatistics(resultDto.homeTeam());
        String secondResult = matchStatisticsService.getTeamStatistics(resultDto.awayTeam());

        return firstResult + "\n" + secondResult;
    }
}
