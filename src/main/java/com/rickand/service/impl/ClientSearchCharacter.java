package com.rickand.service.impl;

import com.rickand.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.rickand.model.Character;

/**
 * The interface Client search character.
 */
@FeignClient(name = "ClientSearchCharacter", url = "${url.service}", configuration = FeignClientConfig.class)
public interface ClientSearchCharacter {
    /**
     * Gets character.
     *
     * @param name the name
     * @return the character
     */
    @GetMapping(value="/character", consumes = MediaType.APPLICATION_JSON_VALUE)
    Character getCharacter(@RequestParam String name);
}
