package com.example.statisticsservice;

import com.example.statisticsservice.controller.dto.MessageDto;
import com.example.statisticsservice.controller.dto.MessageType;
import com.example.statisticsservice.controller.dto.ResultDto;
import com.example.statisticsservice.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StatisticServiceTestIT extends BaseIntegrationTestIT {

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    public void cleanTable() {
        teamRepository.deleteAll();
    }


    @Test
    void shouldReturnOkStatusWhenProperRequestIsMade() throws Exception {
        ResultDto resultDto = new ResultDto("Barcelona", "Real Madrid", 2L, 1L);
        MessageDto messageDto = new MessageDto(MessageType.RESULT, resultDto);

        ResultActions resultActions = mockMvc.perform(post("/statistics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(messageDto)));

        resultActions.andExpect(status().isOk());
        Assertions.assertEquals(2, teamRepository.findAll().size());
    }
}