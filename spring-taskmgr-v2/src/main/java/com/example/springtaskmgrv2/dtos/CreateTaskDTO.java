package com.example.springtaskmgrv2.dtos;

import com.example.springtaskmgrv2.entitie.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskDTO {
    private String title;
    private String description;
    private String dueDate;
    private Status status;
}
