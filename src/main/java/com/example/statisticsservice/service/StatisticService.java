package com.example.statisticsservice.service;

import com.example.statisticsservice.controller.dto.MessageDto;
import com.example.statisticsservice.controller.dto.MessageType;
import com.example.statisticsservice.controller.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final MatchResultService matchResultService;
    private final MatchStatisticsService matchStatisticsService;

    public String fetchStatistics(MessageDto messageDto) {

        if (!MessageType.RESULT.equals(messageDto.type())) {
            throw new RuntimeException("Unsupported type of message");
        }
        ResultDto resultDto = messageDto.result();

        matchResultService.saveMathResults(resultDto);

        String firstResult = matchStatisticsService.getTeamStatistics(resultDto.homeTeam());
        String secondResult = matchStatisticsService.getTeamStatistics(resultDto.awayTeam());

        return firstResult + "\n" + secondResult;
    }
}
