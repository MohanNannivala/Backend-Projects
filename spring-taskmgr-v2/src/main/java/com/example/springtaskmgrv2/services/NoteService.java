package com.example.springtaskmgrv2.services;


import com.example.springtaskmgrv2.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NoteService {

    @Autowired
    NotesRepository notesRepository;

}
