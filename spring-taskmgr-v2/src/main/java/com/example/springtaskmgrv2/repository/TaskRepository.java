package com.example.springtaskmgrv2.repository;

import com.example.springtaskmgrv2.entity.Status;
import com.example.springtaskmgrv2.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByStatus(Status status);


    //Ideally this is 'business logic' terminology (i.e., overdue) so shouldn't be here
    @Query("SELECT t FROM tasks t WHERE (t.status = 'NOT_STARTED' or t.status='WORK_IN_PROGRESS') and t.dueDate < CURRENT_DATE")
    List<TaskEntity> findAllOverdue();
    //or
    //List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

    @Query("SELECT t FROM tasks t WHERE t.title like %?1%")
    List<TaskEntity> findAllByTitle(String title);
    //or
    //List<TaskEntity> findAllByTitleContainingIgnoreCase(String title);

    TaskEntity findByTitle(String title);
}