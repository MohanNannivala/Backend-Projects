package com.example.springtaskmgrv2.dtos;

import com.example.springtaskmgrv2.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskDTO {
    private String title;
    private String description;
    private String dueDate;
    private Status status;
}
