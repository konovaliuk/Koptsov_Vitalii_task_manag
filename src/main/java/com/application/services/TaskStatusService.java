package com.application.services;

import com.application.model.Task;
import com.application.model.TaskStatus;
import com.application.repository.TaskRepository;
import com.application.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class TaskStatusService {
    private static final Logger LOGGER = LogManager.getLogger(TaskStatusService.class);
    private final TaskStatusRepository taskStatusRepository;
    public TaskStatus getTaskStatus(long id) {
        try{
            return taskStatusRepository.findOne(id);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting taskStatus" + id);
        }
    }

    public List<TaskStatus> getAllTaskStatuses(){
        try{
            return taskStatusRepository.findAll();
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all taskStatuses");
        }
    }
    @Transactional(readOnly = true)
    public void updateTaskStatus(TaskStatus taskStatus){
        try{
            taskStatusRepository.change(taskStatus);
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while update taskStatus");
        }
    }
    @Transactional(readOnly = true)
    public TaskStatus createTaskStatus(TaskStatus taskStatus){
        try{
            TaskStatus newTaskStatus = taskStatusRepository.save(taskStatus);
            return newTaskStatus;
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while create taskStatus");
        }
    }
    @Transactional(readOnly = true)
    public void deleteTaskStatus(TaskStatus taskStatus){
        try{
            taskStatusRepository.delete(taskStatus.getId());
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while create taskStatus");
        }
    }
}
