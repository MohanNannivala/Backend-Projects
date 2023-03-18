package com.ooothla.blogapi.users.exceptions;

public class UserNotFoundException extends IllegalArgumentException{

    public UserNotFoundException(Integer id){
        super("User with id: "+ id +" not found");
    }

    public UserNotFoundException(String userName){
        super("User with username: "+userName+" not found");
    }
}
