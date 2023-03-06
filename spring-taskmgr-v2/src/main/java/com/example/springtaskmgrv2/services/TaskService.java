package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final SimpleDateFormat dueDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static class TaskNotFoundException extends IllegalStateException {
        TaskNotFoundException(Integer id){
            super("Task with id "+id+" not found");
        }

    }

    @Autowired
    TaskRepository taskRepository;

    public List<TaskEntity> getAllTasks(){
        return taskRepository.findAll();
    }

    public TaskEntity getTaskById(Integer id){
        TaskEntity task = taskRepository.findById(id).orElse(null);
        return task;
    }

    public TaskEntity addTask(String title, String description, Boolean completed, String dueDate) throws ParseException {

        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        task.setCompleted(completed);
        task.setDueDate(dueDateFormatter.parse(dueDate));

        taskRepository.save(task);

        return task;
    }

    public TaskEntity updateTask(Integer id, String title, String description, Boolean completed, String dueDate) throws ParseException {


        TaskEntity task = getTaskById(id);

        if(title != null) {
            task.setTitle(title);
        }

        if(description != null) {
            task.setDescription(description);
        }

        if(completed != false) {
            task.setCompleted(completed);
        }

        if(dueDate != null) {
            task.setDueDate(dueDateFormatter.parse(dueDate));
        }

        return task;
    }

    public TaskEntity deleteTask(Integer id){
        TaskEntity deletedTask = getTaskById(id);
        taskRepository.deleteById(id);
        return  deletedTask;
    }

    public List<TaskEntity> getTaskByTitle(List<String> title){

        List<TaskEntity> tasks = new ArrayList<>();

        for(int i=0; i<title.size(); i++){
            tasks.add(taskRepository.findByTitle(title.get(i)));
        }

        return tasks;
    }

    public List<TaskEntity> getTaskByCompleted(boolean isCompleted){
        List<TaskEntity> tasks = taskRepository.findAllByCompleted(isCompleted);
        return tasks;
    }
}
