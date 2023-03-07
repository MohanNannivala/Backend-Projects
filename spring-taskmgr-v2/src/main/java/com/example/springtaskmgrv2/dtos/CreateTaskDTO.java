package com.example.springtaskmgrv2.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskDTO {
    private String title;
    private String description;
    private String dueDate;
    private Boolean completed;
}
