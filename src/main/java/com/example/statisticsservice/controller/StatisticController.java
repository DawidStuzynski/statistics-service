package com.example.statisticsservice.controller;

import com.example.statisticsservice.controller.dto.MessageDto;
import com.example.statisticsservice.service.StatisticServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticServiceImpl statisticService;

    @PostMapping
    public ResponseEntity<String> fetchStatistics(@RequestBody MessageDto messageDto) {

        return ResponseEntity.ok().body(statisticService.fetchStatistics(messageDto));
    }
}
