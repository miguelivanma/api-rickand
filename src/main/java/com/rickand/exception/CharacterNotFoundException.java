package com.rickand.exception;

/**
 * The type Character not found exception.
 */
public class CharacterNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Character not found exception.
     *
     * @param name the name
     */
    public CharacterNotFoundException(String name) {
        super("Could not find Character " + name);
    }
}
