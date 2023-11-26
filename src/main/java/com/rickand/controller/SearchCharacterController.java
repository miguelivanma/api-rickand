package com.rickand.controller;

import com.rickand.exception.CharacterNotFoundException;
import com.rickand.model.CharacterAppearance;
import com.rickand.service.SearchCharacterAppearanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Search character controller.
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class SearchCharacterController {
    private final SearchCharacterAppearanceService searchCharacterAppearanceService;

    /**
     * Gets character.
     *
     * @param name the name
     * @return the character
     * @throws CharacterNotFoundException the character not found exception
     */
    @GetMapping("/search-character-appearance")
    public ResponseEntity<CharacterAppearance> getCharacter(@RequestParam String name) throws CharacterNotFoundException {
        return new ResponseEntity<>(searchCharacterAppearanceService.getCharacterAppearance(name), HttpStatus.OK);
    }
}
