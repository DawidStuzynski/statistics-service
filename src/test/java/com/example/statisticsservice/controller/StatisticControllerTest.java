package com.example.statisticsservice.controller;

import com.example.statisticsservice.controller.dto.MessageDto;
import com.example.statisticsservice.controller.dto.MessageType;
import com.example.statisticsservice.controller.dto.ResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StatisticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnOkStatusWhenProperRequestIsMade() throws Exception {
        ResultDto resultDto = new ResultDto("Barcelona", "Real Madrid", 2L, 1L);
        MessageDto messageDto = new MessageDto(MessageType.RESULT, resultDto);

        ResultActions resultActions = mockMvc.perform(post("/statistics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(messageDto)));

        resultActions.andExpect(status().isOk());
    }
}