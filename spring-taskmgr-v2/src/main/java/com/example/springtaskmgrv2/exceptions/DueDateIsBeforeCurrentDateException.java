package com.example.springtaskmgrv2.exceptions;

public class DueDateIsBeforeCurrentDateException extends Exception{
    public DueDateIsBeforeCurrentDateException(){
        super("dueDate should be after current date");
    }
}
