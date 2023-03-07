package com.example.springtaskmgrv2.exception;

public class DueDateIsBeforeCurrentDateException extends Exception{
    public DueDateIsBeforeCurrentDateException(){
        super("dueDate should be after current date");
    }
}
