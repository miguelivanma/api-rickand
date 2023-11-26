package com.rickand.service;

import com.rickand.model.Character;
import com.rickand.service.impl.ClientSearchCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Search character service.
 */
@Service
@RequiredArgsConstructor
public class SearchCharacterService {
    private final ClientSearchCharacter clientSearchCharacter;

    /**
     * Gets character.
     *
     * @param name the name
     * @return the character
     */
    public Character getCharacter(String name) {
        return clientSearchCharacter.getCharacter(name);
    }
}
