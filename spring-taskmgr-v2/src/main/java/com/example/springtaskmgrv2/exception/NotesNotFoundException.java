package com.example.springtaskmgrv2.exception;

public class NotesNotFoundException extends Exception {
    public NotesNotFoundException(Integer notesId) {
        super("Notes with id "+notesId+" not found");
    }
}
