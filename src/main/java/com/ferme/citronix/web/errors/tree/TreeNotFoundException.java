package com.ferme.citronix.web.errors.tree;

public class TreeNotFoundException extends RuntimeException {
    public TreeNotFoundException(String message) {
        super(message);
    }
}
