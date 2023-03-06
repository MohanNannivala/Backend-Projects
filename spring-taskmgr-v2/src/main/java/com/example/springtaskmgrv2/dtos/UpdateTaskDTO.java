package com.example.springtaskmgrv2.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;

@Getter
@Setter
public class UpdateTaskDTO {
    private String title;
    private String description;
    private String dueDate;
    private Boolean completed;
}
