package com.example.springtaskmgrv2.service;


import com.example.springtaskmgrv2.dtos.CreateNotesDTO;
import com.example.springtaskmgrv2.entity.NoteEntity;
import com.example.springtaskmgrv2.entity.TaskEntity;
import com.example.springtaskmgrv2.exception.TaskNotFoundException;
import com.example.springtaskmgrv2.repository.NotesRepository;
import com.example.springtaskmgrv2.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class NoteService {

    private ModelMapper modelMapper=new ModelMapper();

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    TaskRepository taskRepository;

    public NoteEntity addNotes(Integer id, CreateNotesDTO createNotesDTO) throws TaskNotFoundException {

        TaskEntity task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));

        NoteEntity notes = modelMapper.map(createNotesDTO, NoteEntity.class);
        notes.setTask(task);

        return notesRepository.save(notes);
    }

    public NoteEntity deleteNotes(Integer id) throws TaskNotFoundException {
        TaskEntity task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
        return null;
    }
}
