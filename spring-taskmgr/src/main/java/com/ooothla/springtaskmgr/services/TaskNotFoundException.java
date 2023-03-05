package com.ooothla.springtaskmgr.services;

public class TaskNotFoundException extends IllegalArgumentException{
    public TaskNotFoundException(Integer id){
        super("Task with id "+id+" not found");
    }

}
