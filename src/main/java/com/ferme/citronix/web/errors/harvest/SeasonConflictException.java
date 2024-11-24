package com.ferme.citronix.web.errors.harvest;

public class SeasonConflictException extends RuntimeException {
    public SeasonConflictException(String message) {
        super(message);
    }
}
