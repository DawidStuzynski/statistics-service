package com.example.statisticsservice.service;

import com.example.statisticsservice.controller.dto.MessageDto;

public interface StatisticService {

    String fetchStatistics(MessageDto messageDto);
}
