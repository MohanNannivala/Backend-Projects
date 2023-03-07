package com.example.springtaskmgrv2.service;


import com.example.springtaskmgrv2.repositorie.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NoteService {

    @Autowired
    NotesRepository notesRepository;

}
