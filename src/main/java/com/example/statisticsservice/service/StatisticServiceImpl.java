package com.example.statisticsservice.service;

import com.example.statisticsservice.controller.dto.MessageDto;
import com.example.statisticsservice.controller.dto.MessageType;
import com.example.statisticsservice.controller.dto.ResultDto;
import com.example.statisticsservice.exception.NotSupportedTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService{
    private final MatchResultServiceImpl matchResultService;
    private final MatchStatisticsServiceImpl matchStatisticsService;

    @Override
    public String fetchStatistics(MessageDto messageDto) {

        if (!MessageType.RESULT.equals(messageDto.type())) {
            throw new NotSupportedTypeException(messageDto.type().toString());
        }

        ResultDto resultDto = messageDto.result();

        matchResultService.saveMathResults(resultDto);

        String firstResult = matchStatisticsService.getTeamStatistics(resultDto.homeTeam());
        String secondResult = matchStatisticsService.getTeamStatistics(resultDto.awayTeam());

        return firstResult + "\n" + secondResult;
    }
}
