package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class TaskRepositoryTests {

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void testCreateTask(){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("Task 1");
        taskEntity.setDescription("Description of task 1");
        taskEntity.setCompleted(false);
        TaskEntity savedTask = taskRepository.save(taskEntity);
        assertNotNull(savedTask);
    }
}
