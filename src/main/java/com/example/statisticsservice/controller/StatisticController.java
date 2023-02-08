package com.example.statisticsservice.controller;

import com.example.statisticsservice.controller.dto.MessageDto;
import com.example.statisticsservice.controller.dto.MessageType;
import com.example.statisticsservice.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    @PostMapping
    public String fetchStatistics(@RequestBody MessageDto messageDto) {

        if (!MessageType.RESULT.equals(messageDto.type())) {
            throw new RuntimeException("Unsupported type of message");
        }
        return statisticService.fetchStatistics(messageDto.result());
    }
}
