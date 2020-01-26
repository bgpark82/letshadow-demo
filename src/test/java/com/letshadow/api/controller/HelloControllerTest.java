package com.letshadow.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class HelloControllerTest {

    @Autowired
    private HelloController helloController;

    private MockMvc mockMvc;

    private static String url = "/api/v1";

    @BeforeEach
    public void BeforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    void 헬로_테스트() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("hello"));
    }
}