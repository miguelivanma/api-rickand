package com.rickand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Character not found advice.
 */
@ControllerAdvice
public class CharacterNotFoundAdvice {
    /**
     * Character not found handler string.
     *
     * @param ex the ex
     * @return the string
     */
    @ResponseBody
    @ExceptionHandler(CharacterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String characterNotFoundHandler(CharacterNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Episode not found handler string.
     *
     * @param ex the ex
     * @return the string
     */
    @ResponseBody
    @ExceptionHandler(EpisodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String episodeNotFoundHandler(EpisodeNotFoundException ex) {
        return ex.getMessage();
    }
}
