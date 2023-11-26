package com.rickand.service.impl;

import com.rickand.config.FeignClientConfig;
import com.rickand.model.Episode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The interface Client search episode.
 */
@FeignClient(name = "ClientSearchEpisode", url = "${url.service}",configuration = FeignClientConfig.class)
public interface ClientSearchEpisode {
    /**
     * Gets episode.
     *
     * @param id the id
     * @return the episode
     */
    @GetMapping(value="/episode/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Episode getEpisode(@PathVariable String id);
}
