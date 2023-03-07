package com.example.springtaskmgrv2.controllers;

import com.example.springtaskmgrv2.dtos.CreateTaskDTO;
import com.example.springtaskmgrv2.dtos.UpdateTaskDTO;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.services.TaskService;
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
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO createTaskDTO) throws ParseException {

        TaskEntity task=taskService.addTask(createTaskDTO.getTitle(),
                            createTaskDTO.getDescription(),
                            createTaskDTO.getCompleted(),
                            createTaskDTO.getDueDate()
                        );

        return ResponseEntity.created(URI.create("/tasks/"+task.getId())).body(task);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO updateTaskDTO) throws ParseException {
        return ResponseEntity.accepted().body(taskService.updateTask(id,
                                    updateTaskDTO.getTitle(),
                                    updateTaskDTO.getDescription(),
                                    updateTaskDTO.getCompleted(),
                                    updateTaskDTO.getDueDate()));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskEntity> deleteTask(@PathVariable("id") Integer id){
        return ResponseEntity.accepted().body(taskService.deleteTask(id));
    }

    @GetMapping("/tasks/{title}")
    public ResponseEntity<List<TaskEntity>> getTaskByTitle(@RequestParam("title") List<String> title){
        return ResponseEntity.ok(taskService.getTaskByTitle(title));
    }

    @GetMapping("/tasks/{isCompleted}")
    public ResponseEntity<List<TaskEntity>> getTaskByCompleted(@RequestParam("isCompleted") boolean isCompleted){
        return ResponseEntity.ok(taskService.getTaskByCompleted(isCompleted));
    }
}
