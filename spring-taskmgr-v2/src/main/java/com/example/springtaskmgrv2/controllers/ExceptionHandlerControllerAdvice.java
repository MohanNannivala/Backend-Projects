package com.example.springtaskmgrv2.controllers;

import com.example.springtaskmgrv2.dtos.ErrorResponseDTO;
import com.example.springtaskmgrv2.exceptions.DueDateIsBeforeCurrentDateException;
import com.example.springtaskmgrv2.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleTaskNotFoundException(Exception e){
        return new ResponseEntity<>(new ErrorResponseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DueDateIsBeforeCurrentDateException.class})
    public ResponseEntity<ErrorResponseDTO> handleDueDateIsBeforeCurrentDateException(Exception e){
        return new ResponseEntity<>(new ErrorResponseDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
