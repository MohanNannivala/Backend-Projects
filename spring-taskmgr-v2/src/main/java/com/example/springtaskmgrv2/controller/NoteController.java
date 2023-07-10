package com.example.springtaskmgrv2.controller;

import com.example.springtaskmgrv2.dtos.CreateNoteDTO;
import com.example.springtaskmgrv2.dtos.NoteResponseDTO;
import com.example.springtaskmgrv2.entity.NoteEntity;
import com.example.springtaskmgrv2.exception.NotesNotFoundException;
import com.example.springtaskmgrv2.exception.TaskNotFoundException;
import com.example.springtaskmgrv2.service.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NoteController {
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    NoteService noteService;

    @GetMapping("/tasks/{id}/notes")
    public ResponseEntity<List<NoteResponseDTO>> getNotesByTaskId(@PathVariable("id") Integer taskId) throws TaskNotFoundException {
        List<NoteEntity> notes = noteService.getNotesByTaskId(taskId);
        List<NoteResponseDTO> noteResponse = new ArrayList<>();

        for(NoteEntity note : notes){
            noteResponse.add(modelMapper.map(note, NoteResponseDTO.class));
        }

        return ResponseEntity.ok(noteResponse);
    }

    @PostMapping("/tasks/{id}/notes")
    public ResponseEntity<NoteResponseDTO> addNotes(@PathVariable("id") Integer taskId, @RequestBody CreateNoteDTO createNotesDTO) throws TaskNotFoundException {
        NoteEntity note = noteService.addNotes(taskId, createNotesDTO);
        NoteResponseDTO noteResponse = modelMapper.map(note, NoteResponseDTO.class);
        return ResponseEntity.created(URI.create("/tasks/"+taskId+"/notes/"+note.getId())).body(noteResponse);
    }

    @DeleteMapping("/tasks/{id}/notes/{noteId}")
    public ResponseEntity<NoteResponseDTO> deleteNotesByNotesId(@PathVariable("id") Integer taskId, @PathVariable("noteId") Integer notesId) throws TaskNotFoundException, NotesNotFoundException {
        NoteResponseDTO noteResponse = modelMapper.map(noteService.deleteNotesByNotesId(taskId, notesId), NoteResponseDTO.class);
        return ResponseEntity.accepted().body(noteResponse);
    }
}
