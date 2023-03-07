package com.example.springtaskmgrv2.dtos;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.Status;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class TaskResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private Data dueDate;
    private Status status;
    private Date createdAt;
    private List<NoteEntity> notes;
}
