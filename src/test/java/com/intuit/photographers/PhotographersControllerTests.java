package com.intuit.photographers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PhotographersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PhotographersRepository photographersRepository;

    @Test
    void testGetPhotographerById() throws Exception {
        Photographer photographer = new Photographer();
        photographer.setEmail("willia.rowe@email.com");
        photographer.setId(8438);

        EventType eventType = new EventType();
        eventType.setType(new ArrayList<>(Arrays.asList("wedding", "birthdays")));
        photographer.setEvent_type(eventType);

        mockMvc.perform(get("/api/photographers/{photographerID}", 8438L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(photographer)))
                .andExpect(status().isOk());

        Photographer expected = photographersRepository.findById(8438);
        assertThat(expected.getEmail()).isEqualTo("willia.rowe@email.com");
    }

    @Test
    void testGetPhotographerByEvent() throws Exception {
        Photographer photographer = new Photographer();
        photographer.setEmail("willia.rowe@email.com");
        photographer.setId(8438);
        EventType eventType = new EventType();
        eventType.setType(new ArrayList<>(Arrays.asList("wedding", "birthdays")));
        photographer.setEvent_type(eventType);

        mockMvc.perform(get("/api/photographers/event/{eventType}", "wedding")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(photographer)))
                .andExpect(status().isOk());


        Photographer expected = photographersRepository.findById(8438);
        assertThat(expected.getEmail()).isEqualTo("willia.rowe@email.com");

    }
}
