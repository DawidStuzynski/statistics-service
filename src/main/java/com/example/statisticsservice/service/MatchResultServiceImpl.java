package com.example.statisticsservice.service;

import com.example.statisticsservice.controller.dto.ResultDto;
import com.example.statisticsservice.model.Team;
import com.example.statisticsservice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class MatchResultServiceImpl implements MatchResultService {

    private static final Long WINNING_POINTS = 3L;
    private static final Long LOOSING_POINTS = 0L;
    private static final Long DRAFT_POINTS = 1L;

    private final TeamRepository teamRepository;

    @Override
    public void saveMathResults(ResultDto resultDto) {
        if (resultDto.homeScore() > resultDto.awayScore()) {
            saveWinResult(resultDto.homeTeam(), resultDto.awayTeam(), resultDto.homeScore(), resultDto.awayScore());
        }
        if (resultDto.homeScore() < resultDto.awayScore()) {
            saveWinResult(resultDto.awayTeam(), resultDto.homeTeam(), resultDto.awayScore(), resultDto.homeScore());
        }
        if (resultDto.homeScore().equals(resultDto.awayScore())) {
            saveDrawResult(resultDto.homeTeam(), resultDto.awayTeam(), resultDto.homeScore());
        }
    }

    private void saveDrawResult(String homeTeamName, String awayTeamName, Long score) {
        saveDataToProperTeam(homeTeamName, score, score, DRAFT_POINTS);
        saveDataToProperTeam(awayTeamName, score, score, DRAFT_POINTS);
    }

    private void saveWinResult(String winningTeamName, String loosingTeamName, Long winningScore, Long loosingScore) {
        saveDataToProperTeam(winningTeamName, winningScore, loosingScore, WINNING_POINTS);
        saveDataToProperTeam(loosingTeamName, loosingScore, winningScore, LOOSING_POINTS);
    }

    private void saveDataToProperTeam(String teamName, Long earnedGoals, Long lostGoals, Long earnedPoints) {
        Team team = getTeam(teamName);
        team.setPlayedEvents(team.getPlayedEvents() + 1);
        team.setGainedPoints(team.getGainedPoints() + earnedPoints);
        team.setGoalsScored(team.getGoalsScored() + earnedGoals);
        team.setGoalsConceded(team.getGoalsConceded() + lostGoals);
        teamRepository.save(team);
    }

    private Team getTeam(String teamName) {
        Optional<Team> teamOptional = teamRepository.getByName(teamName);
        return teamOptional.orElseGet(() -> teamRepository.save(
                Team.builder()
                        .name(teamName)
                        .playedEvents(0L)
                        .gainedPoints(0L)
                        .goalsScored(0L)
                        .goalsConceded(0L)
                        .build()
        ));
    }
}
