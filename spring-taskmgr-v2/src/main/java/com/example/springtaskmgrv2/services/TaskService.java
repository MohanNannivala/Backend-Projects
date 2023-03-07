package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.Status;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.exceptions.DueDateIsBeforeCurrentDateException;
import com.example.springtaskmgrv2.exceptions.TaskNotFoundException;
import com.example.springtaskmgrv2.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private final SimpleDateFormat dueDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    TaskRepository taskRepository;

    public List<TaskEntity> getAllTasks(){
        return taskRepository.findAll();
    }

    public TaskEntity getTaskById(Integer id){
        TaskEntity task = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
        return task;
    }

    public TaskEntity addTask(String title, String description, Status status, String dueDate) throws ParseException, DueDateIsBeforeCurrentDateException {

        if(dueDateFormatter.parse(dueDate).compareTo(new Date()) < 0){
            throw new DueDateIsBeforeCurrentDateException();
        }

        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setDueDate(dueDateFormatter.parse(dueDate));

        taskRepository.save(task);

        return task;
    }

    public TaskEntity updateTask(Integer id, String title, String description, Status status, String dueDate) throws ParseException, DueDateIsBeforeCurrentDateException {


        TaskEntity task = getTaskById(id);

        if(dueDateFormatter.parse(dueDate).compareTo(new Date()) < 0){
            throw new DueDateIsBeforeCurrentDateException();
        }

        if(title != null) {
            task.setTitle(title);
        }

        if(description != null) {
            task.setDescription(description);
        }

        if(status != null) {
            task.setStatus(status);
        }

        if(dueDate != null) {
            task.setDueDate(dueDateFormatter.parse(dueDate));
        }

        return taskRepository.save(task);
    }

    public TaskEntity deleteTask(Integer id){
        TaskEntity deletedTask = getTaskById(id);
        taskRepository.deleteById(id);
        return  deletedTask;
    }

    public List<TaskEntity> getTaskByTitle(List<String> title){

        List<TaskEntity> tasks = new ArrayList<>();
        TaskEntity task;

        for(int i=0; i<title.size(); i++){
            task=taskRepository.findByTitle(title.get(i));
            if(task!=null){
                tasks.add(task);
            }
        }

        if(tasks.isEmpty()){
            throw new TaskNotFoundException(title);
        }

        return tasks;
    }

    public List<TaskEntity> getTaskByCompleted(Status status){

        List<TaskEntity> tasks = taskRepository.findAllByStatus(status);

        if(tasks.isEmpty()){
            throw new TaskNotFoundException(status);
        }

        return tasks;
    }
}
