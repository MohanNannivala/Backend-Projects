package com.example.springtaskmgrv2.controller;

import com.example.springtaskmgrv2.dtos.CreateTaskDTO;
import com.example.springtaskmgrv2.dtos.UpdateTaskDTO;
import com.example.springtaskmgrv2.entity.Status;
import com.example.springtaskmgrv2.entity.TaskEntity;
import com.example.springtaskmgrv2.exception.DueDateIsBeforeCurrentDateException;
import com.example.springtaskmgrv2.exception.TaskNotFoundException;
import com.example.springtaskmgrv2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskEntity>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id) throws TaskNotFoundException {
        return ResponseEntity.ok(taskService.getTaskById(id));
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
