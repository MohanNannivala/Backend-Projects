package com.example.springtaskmgrv2.service;


import com.example.springtaskmgrv2.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NoteService {

    @Autowired
    NotesRepository notesRepository;


}
