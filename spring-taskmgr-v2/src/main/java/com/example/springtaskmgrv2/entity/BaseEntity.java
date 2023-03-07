package com.example.springtaskmgrv2.entity;

import javax.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private  Integer id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
}
