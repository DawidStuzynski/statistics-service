package com.example.statisticsservice.service;

import com.example.statisticsservice.model.Team;
import com.example.statisticsservice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MatchStatisticsService {


    private final TeamRepository teamRepository;

    public String getTeamStatistics(String teamName) {
        Team team = teamRepository.getByName(teamName).orElseThrow(() -> new RuntimeException("Something went wrong"));
        return String.format("%s %d %d %d %d", team.getName(), team.getPlayedEvents(), team.getGainedPoints(),
                team.getGoalsScored(), team.getGoalsConceded());
    }
}
