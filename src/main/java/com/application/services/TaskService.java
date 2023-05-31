package com.application.services;

import com.application.dao.ConnectionPool;
import com.application.dao.DaoFactory;
import com.application.model.Task;
import com.application.model.TaskRole;
import com.application.model.TaskUser;
import com.application.model.User;
import com.application.repository.TaskRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
    private static final Logger LOGGER = LogManager.getLogger(TaskService.class);
    private final TaskRepository taskRepository;
    public Task getTask(long id) {
        try{
            return taskRepository.findOne(id);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting task");
        }
    }

    public List<Task> getAllTasks(){
        try{
            return taskRepository.findAll();
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all tasks");
        }
    }
    @Transactional(readOnly = true)
    public void updateTask(Task task){
        try{
            taskRepository.change(task);
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while update task");
        }
    }
    @Transactional(readOnly = true)
    public Task createTask(Task task){
        try{
            Task newTask = taskRepository.save(task);
            return newTask;
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while create task");
        }
    }
    @Transactional(readOnly = true)
    public void deleteTask(Task task){
        try{
            taskRepository.delete(task.getId());
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while create task");
        }
    }
}
