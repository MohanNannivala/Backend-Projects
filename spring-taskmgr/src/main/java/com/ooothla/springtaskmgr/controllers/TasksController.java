package com.ooothla.springtaskmgr.controllers;

import com.ooothla.springtaskmgr.dtos.ErrorResponse;
import com.ooothla.springtaskmgr.entities.Task;
import com.ooothla.springtaskmgr.services.DueDateException;
import com.ooothla.springtaskmgr.services.TaskNotFoundException;
import com.ooothla.springtaskmgr.services.TasksServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class  TasksController {

    private TasksServices tasksServices;

    public TasksController(TasksServices tasksServices){
        this.tasksServices=tasksServices;
    }

    /**
     * Show all existing tasks
     * GET /tasks
     * @return
     */

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks(){
       return ResponseEntity.ok(tasksServices.getTasks());
    }

    /**
     * Create a new task
     * POST /tasks
     * Body:
     *  <pre>
     *      {
     *          "title": "Task 4",
     *          "description": "Description 4",
     *          "dueDate": "2023-01-01"
     *      }
     *  </pre>
     * @param task Task object sent from client
     * @return Task object created
     */

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        Task newTask=tasksServices.addTask(task);
        return ResponseEntity.created(URI.create("/tasks/"+newTask.getId())).body(newTask);
    }

    /**
     * Get a task by id
     * @param id
     * @return Task object
     */
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Integer id){
        return ResponseEntity.ok(tasksServices.getTaskById(id));
    }

    /**
     * Delete a task by given id
     * @param id Task id to delete
     * @return the deleted task
     */

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Integer id){
        return ResponseEntity.accepted().body(tasksServices.deleteTask(id));
    }

    /**
     * Update a task by given id
     * @param id Task id to update
     * @param task Task object sent by client
     * @return the updated task
     */

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @RequestBody Task task){
        return ResponseEntity.accepted().body(tasksServices.updateTask(
                id,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate()
        ));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(TaskNotFoundException e){
        return new ResponseEntity<>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DueDateException.class)
    ResponseEntity<ErrorResponse> handleErrors(DueDateException e){
        return new ResponseEntity<>(
                new ErrorResponse((e.getMessage())),
                HttpStatus.NOT_FOUND
        );
    }
}
