package com.example.springtaskmgrv2.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

import java.util.Date;


@Entity(name = "tasks")
@Data
public class TaskEntity extends BaseEntity{

    @Column(name = "title", nullable = false, length = 150, unique = true)
    String title;

    @Column(name = "description", nullable = true, length = 500)
    String description;

    @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
    Boolean completed;

    @Column(name = "due_date", nullable = true)
    Date dueDate;

}
