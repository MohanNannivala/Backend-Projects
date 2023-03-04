package com.ooothla.springtaskmgr.services;


import com.ooothla.springtaskmgr.entities.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TasksServices {

    private final List<Task> taskList = new ArrayList<>();
    private AtomicInteger taskId = new AtomicInteger(0);

    public TasksServices(){
        taskList.add(new Task(taskId.incrementAndGet(), "Task 1", "Description 1", new Date()));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 2", "Description 2", new Date()));
        taskList.add(new Task(taskId.incrementAndGet(), "Task 3", "Description 3", new Date()));
    }

    public List<Task> getTasks(){
        return taskList;
    }

    public Task addTask(Task task){


        if(task.getDueDate().compareTo(new Date()) < 0){
            throw new DueDateException();
        }

        Task newTask = new Task(taskId.incrementAndGet(), task.getTitle(), task.getDescription(), task.getDueDate());
        taskList.add(newTask);

        return newTask;
    }

    public Task getTaskById(Integer id){

        for(int i=0; i<taskList.size(); i++){
            if(taskList.get(i).getId()==id){
                return taskList.get(i);
            }
        }

        throw new TaskNotFoundException(id);
    }

    public Task deleteTask(Integer id){

        Task deletedTask=getTaskById(id);
        taskList.remove(deletedTask);
        return deletedTask;

    }

    public Task updateTask(Integer id, String title, String description, Date dueDate){

        Task task = getTaskById(id);

        if(title != null) task.setTitle(title);
        if(description != null) task.setDescription(description);
        if(dueDate !=null) task.setDueDate(dueDate);

        return task;

    }

}
