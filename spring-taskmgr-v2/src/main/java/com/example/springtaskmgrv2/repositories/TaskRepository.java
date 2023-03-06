package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByCompleted(boolean completed);

    //Ideally this is 'business logic' terminology (i.e., overdue) so shouldn't be here
    @Query("SELECT t FROM tasks t WHERE t.completed = false and t.dueDate < CURRENT_DATE")
    List<TaskEntity> findAllOverdue();
    //or
    //List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

    @Query("SELECT t FROM tasks t WHERE t.title like %?1%")
    List<TaskEntity> findAllByTitle(String title);
    //or
    //List<TaskEntity> findAllByTitleContainingIgnoreCase(String title);

    TaskEntity findByTitle(String title);
}