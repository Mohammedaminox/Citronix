package com.ferme.citronix.web.errors.field;

import java.util.UUID;

public class InvalidTreeDensityException extends RuntimeException {
    public InvalidTreeDensityException(UUID fieldId, int numberOfTrees) {
        super("Invalid tree density: Field with ID " + fieldId + " cannot accommodate " + numberOfTrees + " trees.");
    }
}
