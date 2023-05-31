package com.application.services;

import com.application.dao.DaoFactory;
import com.application.model.Task;
import com.application.model.UserRole;
import com.application.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleService{
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private final UserRoleRepository userRoleRepository;
    public UserRole getUserRole(long id) {
        try{
            return userRoleRepository.findOne(id);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting task");
        }
    }

    public List<UserRole> getAllUserRole(){
        try{
            return userRoleRepository.findAll();
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all tasks");
        }
    }
    @Transactional(readOnly = true)
    public void updateUserRole(UserRole task){
        try{
            userRoleRepository.change(task);
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while update task");
        }
    }
    @Transactional(readOnly = true)
    public UserRole createUserRole(UserRole task){
        try{
            UserRole userRole = userRoleRepository.save(task);
            return userRole;
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while create task");
        }
    }
    @Transactional(readOnly = true)
    public void deleteUserRole(UserRole userRole){
        try{
            userRoleRepository.delete(userRole.getId());
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while create task");
        }
    }
}
