package com.example.springtaskmgrv2.entities;

import javax.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private  Integer id;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
}
