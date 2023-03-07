package com.example.springtaskmgrv2.exception;

import com.example.springtaskmgrv2.entity.Status;

import java.util.List;

public class TaskNotFoundException extends Exception{
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
