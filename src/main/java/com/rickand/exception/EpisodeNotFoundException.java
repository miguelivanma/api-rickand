package com.rickand.exception;

/**
 * The type Episode not found exception.
 */
public class EpisodeNotFoundException extends RuntimeException{
    /**
     * Instantiates a new Episode not found exception.
     */
    public EpisodeNotFoundException() {
        super("Could not find episode");
    }
}
