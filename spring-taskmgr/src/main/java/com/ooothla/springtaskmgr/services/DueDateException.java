package com.ooothla.springtaskmgr.services;

public class DueDateException extends  IllegalStateException{
    public DueDateException(){
        super("You are trying to create the task for a previous date. You can only create for future date");
    }
}
