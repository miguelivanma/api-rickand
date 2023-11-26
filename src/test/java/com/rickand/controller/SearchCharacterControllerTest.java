package com.rickand.controller;

import com.rickand.exception.CharacterNotFoundException;
import com.rickand.exception.EpisodeNotFoundException;
import com.rickand.model.CharacterAppearance;
import com.rickand.service.SearchCharacterAppearanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SearchCharacterController.class)
public class SearchCharacterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SearchCharacterAppearanceService service;
    private CharacterAppearance characterAppearance;
    private ArrayList<String> list = new ArrayList<String>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        //controller = new SearchCharacterController(service);
        list.add("Pilot");
        characterAppearance = buildcharacterAppearance();
    }

    @Test
    public void getCharacterAppearance() throws Exception {
        when(service.getCharacterAppearance("Rick+Sanchez")).thenReturn(characterAppearance);
        mockMvc.perform(get("/search-character-appearance")
                        .param("name", "Rick+Sanchez")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rick Sanchez"))
                .andExpect(jsonPath("$.episodes").value(list))
                .andExpect(jsonPath("$.first_apppearance").value("10/03/2023"));
        verify(service, times(1)).getCharacterAppearance("Rick+Sanchez");
    }

    @Test()
    public void getCharacterAppearance_CharacterNotFound() throws Exception {
        when(service.getCharacterAppearance("Rick+Sanchez")).thenThrow(CharacterNotFoundException.class);
        mockMvc.perform(get("/search-character-appearance")
                        .param("name", "Rick+Sanchez")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test()
    public void getCharacterAppearance_EpisodeNotFound() throws Exception {
        when(service.getCharacterAppearance("Rick+Sanchez")).thenThrow(EpisodeNotFoundException.class);
        mockMvc.perform(get("/search-character-appearance")
                        .param("name", "Rick+Sanchez")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private CharacterAppearance buildcharacterAppearance() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Pilot");
        return CharacterAppearance.builder()
                .name("Rick Sanchez")
                .episodes(list)
                .firstApppearance("10/03/2023")
                .build();
    }
}