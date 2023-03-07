package com.example.springtaskmgrv2.exceptions;

import com.example.springtaskmgrv2.entities.Status;

import java.util.List;

public class TaskNotFoundException extends IllegalStateException{
    public TaskNotFoundException(Integer id){
        super("Task with id "+id+" not found");
    }
    public TaskNotFoundException(List<String> title){
        super("Tasks with title "+title+" not found");
    }

    public TaskNotFoundException(Status status){
        super("Tasks with status "+status+" not found");
    }
}
