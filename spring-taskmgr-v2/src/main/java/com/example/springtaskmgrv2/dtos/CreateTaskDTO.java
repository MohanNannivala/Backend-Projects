package com.example.springtaskmgrv2.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;

@Getter
@Setter
public class CreateTaskDTO {
    private String title;
    private String description;
    private String dueDate;
    private Boolean completed;
}
