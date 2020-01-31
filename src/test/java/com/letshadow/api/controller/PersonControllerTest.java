package com.letshadow.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letshadow.api.domain.Birthday;
import com.letshadow.api.domain.Person;
import com.letshadow.api.exception.dto.ErrorCode;
import com.letshadow.api.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PersonControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PersonService personService;


    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
    }

    @Test
    void getList() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/person")
                    .param("page","0")
                    .param("size","2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.numberOfElements").value(1))
                .andExpect(jsonPath("$.content.[0].name").value("peter"));
    }

    @Test
    void getPerson() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("peter"));
    }

    @Test
    void savePerson() throws Exception {

        Person.PersonDTO personDTO = Person.PersonDTO.of("gyu","대구", LocalDate.of(1992,12,12),"student","010-1111-1111");

        mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/api/v1/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJsonString(personDTO)))
                .andExpect(status().isCreated());

        Person result = personService.getPersonByName("gyu");
        assertAll(
                ()->assertThat(result.getName()).isEqualTo("gyu"),
                ()->assertThat(result.getAddress()).isEqualTo("대구"),
                ()->assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.of(1992,12,12))),
                ()->assertThat(result.getJob()).isEqualTo("student"),
                ()->assertThat(result.getPhoneNumber()).isEqualTo("010-1111-1111")
        );
    }

    @Test
    void savePersonIfNameIsNull() throws Exception {
        Person.PersonDTO dto = new Person.PersonDTO();

        mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/api/v1/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJsonString(dto)))
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수값입니다"));
    }

    @Test
    void updatePersonIfNameIsDifferent() throws Exception {
        Person.PersonDTO dto = Person.PersonDTO.of("jame","판교",LocalDate.now(),"programmer","010-1111-1111");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/v1/person/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ErrorCode.RENAME_NOT_PERMITTED.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.RENAME_NOT_PERMITTED.getMessage()));
    }

    private String toJsonString(Person.PersonDTO personDTO) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDTO);
    }
}