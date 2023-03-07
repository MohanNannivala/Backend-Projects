package com.example.springtaskmgrv2.controller;

import com.example.springtaskmgrv2.dtos.CreateTaskDTO;
import com.example.springtaskmgrv2.dtos.NoteResponseDTO;
import com.example.springtaskmgrv2.dtos.TaskResponseDTO;
import com.example.springtaskmgrv2.dtos.UpdateTaskDTO;
import com.example.springtaskmgrv2.entity.NoteEntity;
import com.example.springtaskmgrv2.entity.Status;
import com.example.springtaskmgrv2.entity.TaskEntity;
import com.example.springtaskmgrv2.exception.DueDateIsBeforeCurrentDateException;
import com.example.springtaskmgrv2.exception.TaskNotFoundException;
import com.example.springtaskmgrv2.repository.TaskRepository;
import com.example.springtaskmgrv2.service.NoteService;
import com.example.springtaskmgrv2.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    TaskService taskService;

    @Autowired
    NoteService noteService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskEntity>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id) throws TaskNotFoundException {

        TaskResponseDTO taskRepositoryDTO = modelMapper.map(taskService.getTaskById(id), TaskResponseDTO.class);
        List<NoteResponseDTO> notes = new ArrayList<>();

        for(NoteEntity note : noteService.getNotesByTaskId(taskRepositoryDTO.getId())){
            notes.add(modelMapper.map(note, NoteResponseDTO.class));
        }

        taskRepositoryDTO.setNotes(notes);

        return ResponseEntity.ok(taskRepositoryDTO);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO createTaskDTO) throws ParseException, DueDateIsBeforeCurrentDateException {

        TaskEntity task=taskService.addTask(createTaskDTO);

        return ResponseEntity.created(URI.create("/tasks/"+task.getId())).body(task);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO updateTaskDTO) throws ParseException, DueDateIsBeforeCurrentDateException, TaskNotFoundException {

        return ResponseEntity.accepted().body(taskService.updateTask(id,updateTaskDTO));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskEntity> deleteTask(@PathVariable("id") Integer id) throws TaskNotFoundException {
        return ResponseEntity.accepted().body(taskService.deleteTask(id));
    }

    @GetMapping("/tasks/title")
    public ResponseEntity<List<TaskEntity>> getTaskByTitle(@RequestParam("title") List<String> title) throws TaskNotFoundException {
        return ResponseEntity.ok(taskService.getTaskByTitle(title));
    }

    @GetMapping("/tasks/status")
    public ResponseEntity<List<TaskEntity>> getTaskByCompleted(@RequestParam("status") Status status) throws TaskNotFoundException {
        return ResponseEntity.ok(taskService.getTaskByCompleted(status));
    }
}
