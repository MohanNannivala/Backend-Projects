package com.example.springtaskmgrv2.service;

import com.example.springtaskmgrv2.dtos.CreateTaskDTO;
import com.example.springtaskmgrv2.dtos.UpdateTaskDTO;
import com.example.springtaskmgrv2.entitie.Status;
import com.example.springtaskmgrv2.entitie.TaskEntity;
import com.example.springtaskmgrv2.exception.DueDateIsBeforeCurrentDateException;
import com.example.springtaskmgrv2.exception.TaskNotFoundException;
import com.example.springtaskmgrv2.repositorie.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private final SimpleDateFormat dueDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    TaskRepository taskRepository;

    public List<TaskEntity> getAllTasks(){
        return taskRepository.findAll();
    }

    public TaskEntity getTaskById(Integer id) throws TaskNotFoundException {
        TaskEntity task = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
        return task;
    }

    public TaskEntity addTask(CreateTaskDTO createTaskDTO) throws ParseException, DueDateIsBeforeCurrentDateException {

        if(dueDateFormatter.parse(createTaskDTO.getDueDate()).compareTo(new Date()) < 0){
            throw new DueDateIsBeforeCurrentDateException();
        }

        TaskEntity task= modelMapper.map(createTaskDTO, TaskEntity.class);

        taskRepository.save(task);

        return task;
    }

    public TaskEntity updateTask(Integer id, UpdateTaskDTO updateTaskDTO) throws ParseException, DueDateIsBeforeCurrentDateException, TaskNotFoundException {


        TaskEntity task = getTaskById(id);

        if(dueDateFormatter.parse(updateTaskDTO.getDueDate()).compareTo(new Date()) < 0){
            throw new DueDateIsBeforeCurrentDateException();
        }

        if(updateTaskDTO.getTitle() != null) {
            task.setTitle(updateTaskDTO.getTitle());
        }

        if(updateTaskDTO.getDescription() != null) {
            task.setDescription(updateTaskDTO.getDescription());
        }

        if(updateTaskDTO.getStatus() != null) {
            task.setStatus(updateTaskDTO.getStatus());
        }

        if(updateTaskDTO.getDueDate() != null) {
            task.setDueDate(dueDateFormatter.parse(updateTaskDTO.getDueDate()));
        }

        return taskRepository.save(task);
    }

    public TaskEntity deleteTask(Integer id) throws TaskNotFoundException {
        TaskEntity deletedTask = getTaskById(id);
        taskRepository.deleteById(id);
        return  deletedTask;
    }

    public List<TaskEntity> getTaskByTitle(List<String> title) throws TaskNotFoundException {

        List<TaskEntity> tasks = new ArrayList<>();
        TaskEntity task;

        for(int i=0; i<title.size(); i++){
            task=taskRepository.findByTitle(title.get(i));
            if(task!=null){
                tasks.add(task);
            }
        }

        if(tasks.isEmpty()){
            throw new TaskNotFoundException(title);
        }

        return tasks;
    }

    public List<TaskEntity> getTaskByCompleted(Status status) throws TaskNotFoundException {

        List<TaskEntity> tasks = taskRepository.findAllByStatus(status);

        if(tasks.isEmpty()){
            throw new TaskNotFoundException(status);
        }

        return tasks;
    }
}
