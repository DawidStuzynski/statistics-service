package com.example.statisticsservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResultDto(
        @JsonProperty("home_team")
        String homeTeam,

        @JsonProperty("away_team")
        String awayTeam,

        @JsonProperty("home_score")
        Long homeScore,

        @JsonProperty("away_score")
        Long awayScore
) {
}
