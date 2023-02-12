package com.example.statisticsservice;

import com.example.statisticsservice.controller.dto.MessageDto;
import com.example.statisticsservice.controller.dto.MessageType;
import com.example.statisticsservice.controller.dto.ResultDto;
import com.example.statisticsservice.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        //given
        ResultDto resultDto = new ResultDto("Barcelona", "Real Madrid", 2L, 1L);
        MessageDto messageDto = new MessageDto(MessageType.RESULT, resultDto);

        //when
        MvcResult mvcResult = mockMvc.perform(post("/statistics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messageDto)))
                .andExpect(status().isOk())
                .andReturn();

        //then
        String result = mvcResult.getResponse().getContentAsString();
        String expectedResult = "Barcelona 1 3 2 1\nReal Madrid 1 0 1 2";
        assertEquals(expectedResult, result);
        assertEquals(2, teamRepository.findAll().size());
    }
}