package com.ooothla.springtaskmgr.controllers;

import com.ooothla.springtaskmgr.entities.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TasksController {

    private final List<Task> taskList = new ArrayList<>();
    private AtomicInteger taskId = new AtomicInteger(0);

    TasksController(){
        taskList.add(new Task(taskId.incrementAndGet(), "Task 1", "Description 1", new Date()));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 2", "Description 2", new Date()));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 3", "Description 3", new Date()));
    }

    /**
     * Show all existing tasks
     * GET /tasks
     * @return
     */

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskList;
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
        Task newTask = new Task(taskId.incrementAndGet(), task.getTitle(), task.getDescription(), task.getDueDate());
        taskList.add(newTask);
        return newTask;
    }

    /**
     * Get a task by id
     * @param id
     * @return Task object
     */
    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") Integer id){

        for(int i=0; i<taskList.size(); i++){
            if(taskList.get(i).getId()==id){
                return taskList.get(i);
            }
        }

        return null;
    }

    /**
     * Delete a task by given id
     * @param id Task id to delete
     * @return the deleted task
     */

    @DeleteMapping("/tasks/{id}")
    public Task deleteTask(@PathVariable("id") Integer id){

        Task deletedTask=null;

        for(int i=0; i<taskList.size(); i++){
            if(taskList.get(i).getId()==id){
                deletedTask=taskList.get(i);
                taskList.remove(i);
                return deletedTask;
            }
        }

        return null;
    }

    /**
     * Update a task by given id
     * @param id Task id to update
     * @param task Task object sent by client
     * @return the updated task
     */

    @PatchMapping("/tasks/{id}")
    public Task updateTask(@PathVariable("id") Integer id, @RequestBody Task task){

        for(int i=0; i<taskList.size(); i++){
            if(taskList.get(i).getId()==id){

                if(task.getId() !=null){
                    taskList.get(i).setId(task.getId());
                }

                if(task.getTitle() != null){
                    taskList.get(i).setTitle(task.getTitle());
                }

                if(task.getDescription() != null){
                    taskList.get(i).setDescription(task.getDescription());
                }

                if(task.getDueDate()!=null){
                    taskList.get(i).setDueDate(task.getDueDate());
                }

                return taskList.get(i);
            }
        }

        return null;

    }
}
