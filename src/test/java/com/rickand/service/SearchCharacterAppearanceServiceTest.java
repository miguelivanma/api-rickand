package com.rickand.service;

import com.rickand.exception.CharacterNotFoundException;
import com.rickand.exception.EpisodeNotFoundException;
import com.rickand.model.Character;
import com.rickand.model.Episode;
import com.rickand.model.Info;
import com.rickand.model.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchCharacterAppearanceServiceTest {
    @Mock
    private SearchCharacterService searchCharacterService;
    @Mock
    private SearchEpisodeService searchEpisodeService;
    @InjectMocks
    private SearchCharacterAppearanceService service;
    private Character character;
    private Episode episode;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new SearchCharacterAppearanceService(searchCharacterService, searchEpisodeService);
        character = buildCharacter();
        episode = buildEpisode();
    }

    @Test
    public void getCharacterAppearance() {
        String name = "Rick+Sanchez";
        when(searchCharacterService.getCharacter(name)).thenReturn(character);
        when(searchEpisodeService.getEpisode("1")).thenReturn(episode);
        service.getCharacterAppearance(name);
        verify(searchCharacterService, times(1)).getCharacter("Rick+Sanchez");
    }

    @Test
    public void getCharacterAppearance_CharacterNotFound() {
        String name = "Rick+Sanchez";
        character.setResults(null);
        when(searchCharacterService.getCharacter(name)).thenReturn(character);
        assertThrows(CharacterNotFoundException.class, () -> service.getCharacterAppearance(name));
    }

    @Test
    public void getCharacterAppearance_EpisodeNotFound() {
        String name = "Rick+Sanchez";
        when(searchCharacterService.getCharacter(name)).thenReturn(character);
        when(searchEpisodeService.getEpisode("1")).thenThrow(RuntimeException.class);
        assertThrows(EpisodeNotFoundException.class, () -> service.getCharacterAppearance(name));
    }

    private Character buildCharacter() {
        ArrayList<Result> list = new ArrayList<Result>();
        list.add(buildResult());
        return Character.builder()
                .results(list)
                .info(Info.builder().count(1).pages(1).prev("1").next("1").build())
                .build();
    }

    private Result buildResult() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("https://rickandmortyapi.com/api/episode/1");
        return Result.builder()
                .id(1)
                .name("Rick Sanchez")
                .status("Alive")
                .species("Human")
                .type("Type")
                .gender("Male")
                .episode(list)
                .build();
    }

    private Episode buildEpisode() {
        return Episode.builder()
                .name("Pilot")
                .airDate("December 2, 2013")
                .build();
    }

}