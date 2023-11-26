package com.rickand.service;

import com.rickand.model.Character;
import com.rickand.model.Info;
import com.rickand.model.Result;
import com.rickand.service.impl.ClientSearchCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchCharacterServiceTest {
    @Mock
    private ClientSearchCharacter client;
    @InjectMocks
    private SearchCharacterService service;
    private Character character;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new SearchCharacterService(client);
        character = buildCharacter();
    }

    @Test
    public void getCharacter() {
        String name = "Rick+Sanchez";
        when(client.getCharacter(name)).thenReturn(character);
        service.getCharacter(name);
        verify(client, times(1)).getCharacter("Rick+Sanchez");
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
}