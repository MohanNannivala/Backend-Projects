package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void readTasksWorks(){

        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setTitle("Task 1");
        taskEntity1.setDescription("Description of task 1");
        taskEntity1.setCompleted(false);

        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity2.setTitle("Task 1");
        taskEntity2.setDescription("Description of task 1");
        taskEntity2.setCompleted(false);

        taskRepository.save(taskEntity1);
        taskRepository.save(taskEntity2);

        List<TaskEntity> tasks = taskRepository.findAll();

        assertNotNull(tasks);
        assertEquals(2, tasks.size());

    }

    @Test
    public void findByCompletedWorks(){

        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setTitle("Task 1");
        taskEntity1.setDescription("Description of task 1");
        taskEntity1.setCompleted(false);

        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity2.setTitle("Task 1");
        taskEntity2.setDescription("Description of task 1");
        taskEntity2.setCompleted(true);

        taskRepository.save(taskEntity1);
        taskRepository.save(taskEntity2);

        List<TaskEntity> completedTasks = taskRepository.findAllByCompleted(true);
        List<TaskEntity> incompleteTasks = taskRepository.findAllByCompleted(false);

        assertEquals(1, completedTasks.size());
        assertEquals(1, incompleteTasks.size());

    }

}