package com.rickand.service;

import com.rickand.model.Episode;
import com.rickand.service.impl.ClientSearchEpisode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchEpisodeServiceTest {
    @Mock
    private ClientSearchEpisode client;
    @InjectMocks
    private SearchEpisodeService service;
    private Episode episode;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new SearchEpisodeService(client);
        episode = buildEpisode();
    }

    @Test
    public void getEpisode() {
        when(client.getEpisode("1")).thenReturn(episode);
        service.getEpisode("1");
        verify(client, times(1)).getEpisode("1");
    }

    private Episode buildEpisode() {
        return Episode.builder()
                .name("Pilot")
                .airDate("December 2, 2013")
                .build();
    }
}