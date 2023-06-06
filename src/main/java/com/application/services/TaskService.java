package com.application.services;

import com.application.model.Task;
import com.application.model.TaskStatus;
import com.application.repository.TaskRepository;
import com.application.repository.TaskStatusRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
    private static final Logger LOGGER = LogManager.getLogger(TaskService.class);
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
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
    public void updateTask(Task task, Long taskStatusId){
        TaskStatus status = taskStatusRepository.findOne(taskStatusId);
        task.setStatus(status);
        updateTask(task);
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
