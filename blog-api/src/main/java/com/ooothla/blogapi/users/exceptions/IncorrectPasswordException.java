package com.ooothla.blogapi.users.exceptions;

public class IncorrectPasswordException extends IllegalArgumentException{

    public IncorrectPasswordException(){
        super("Incorrect password");
    }
}
