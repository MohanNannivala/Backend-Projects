package com.ooothla.springtaskmgr.controllers;

import com.ooothla.springtaskmgr.entities.Task;
import com.ooothla.springtaskmgr.services.TasksServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TasksController {

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
    public List<Task> getTasks(){
       return tasksServices.getTasks();
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
    public Task addTask(@RequestBody Task task){
        return tasksServices.addTask(task);
    }

    /**
     * Get a task by id
     * @param id
     * @return Task object
     */
    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") Integer id){
        return tasksServices.getTaskById(id);
    }

    /**
     * Delete a task by given id
     * @param id Task id to delete
     * @return the deleted task
     */

    @DeleteMapping("/tasks/{id}")
    public Task deleteTask(@PathVariable("id") Integer id){
        return tasksServices.deleteTask(id);
    }

    /**
     * Update a task by given id
     * @param id Task id to update
     * @param task Task object sent by client
     * @return the updated task
     */

    @PatchMapping("/tasks/{id}")
    public Task updateTask(@PathVariable("id") Integer id, @RequestBody Task task){
        return tasksServices.updateTask(
                id,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate()
        );
    }
}
