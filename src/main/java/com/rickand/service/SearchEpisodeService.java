package com.rickand.service;

import com.rickand.model.Episode;
import com.rickand.service.impl.ClientSearchEpisode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Search episode service.
 */
@Service
@RequiredArgsConstructor
public class SearchEpisodeService {
    private final ClientSearchEpisode clientSearchEpisode;

    /**
     * Gets episode.
     *
     * @param id the id
     * @return the episode
     */
    public Episode getEpisode(String id) {
        return clientSearchEpisode.getEpisode(id);
    }
}
