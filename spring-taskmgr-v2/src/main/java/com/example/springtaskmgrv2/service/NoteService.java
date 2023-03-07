package com.example.springtaskmgrv2.service;


import com.example.springtaskmgrv2.dtos.CreateNoteDTO;
import com.example.springtaskmgrv2.entity.NoteEntity;
import com.example.springtaskmgrv2.entity.TaskEntity;
import com.example.springtaskmgrv2.exception.NotesNotFoundException;
import com.example.springtaskmgrv2.exception.TaskNotFoundException;
import com.example.springtaskmgrv2.repository.NotesRepository;
import com.example.springtaskmgrv2.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private ModelMapper modelMapper=new ModelMapper();

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    TaskRepository taskRepository;


    public List<NoteEntity> getNotesByTaskId(Integer taskId) throws TaskNotFoundException {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException(taskId));
        return notesRepository.findAllByTask(task);
    }
    public NoteEntity addNotes(Integer taskId, CreateNoteDTO createNotesDTO) throws TaskNotFoundException {

        TaskEntity task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException(taskId));
        NoteEntity notes = modelMapper.map(createNotesDTO, NoteEntity.class);
        notes.setTask(task);

        return notesRepository.save(notes);
    }

    public NoteEntity deleteNotesByNotesId(Integer taskId, Integer notesId) throws TaskNotFoundException, NotesNotFoundException {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException(taskId));
        NoteEntity deletedNote = notesRepository.findById(notesId).orElseThrow(() -> new NotesNotFoundException(notesId));
        notesRepository.deleteByTaskIdAndNoteId(task, notesId);
        return deletedNote;
    }
}
