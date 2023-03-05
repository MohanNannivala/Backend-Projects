package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
