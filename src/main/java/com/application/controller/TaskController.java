package com.application.controller;

import com.application.model.Task;
import com.application.model.TaskStatus;

import java.sql.Timestamp;
import java.util.List;

import com.application.services.TaskService;
import com.application.services.TaskStatusService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private static final Logger LOGGER = LogManager.getLogger(TaskController.class);
    @Autowired
    TaskService taskService;
    @Autowired
    TaskStatusService taskStatusService;
    @GetMapping(value = "")
    public String showTaskById(@RequestParam("id") int id, HttpSession session, Model model) {
        LOGGER.error("GET /task/" + id);
        if(session.getAttribute("user") == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Task task = taskService.getTask(id);
        if(task == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("task", task);
        return "task/task";

    }
    @GetMapping(value = "/edit")
    public String showTaskEditPageById(@RequestParam("id") int id, HttpSession session, Model model)
    {
        LOGGER.error("GET /task/edit/" + id);
        if(session.getAttribute("user") == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Task task = taskService.getTask(id);
        if(task == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<TaskStatus> taskStatusList = taskStatusService.getAllTaskStatuses();
        model.addAttribute("task", task);
        model.addAttribute("taskStatusList", taskStatusList);
        return "task/task_edit";
    }

    @GetMapping(value = "all")
    public String showTasks(HttpSession session, Model model)
    {
        LOGGER.error("GET /task/all");
        if(session.getAttribute("user") == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        List<Task> taskList = taskService.getAllTasks();
        model.addAttribute("taskList", taskList);
        return "task/tasks";
    }

    @PostMapping(value = "/edit")
    public String editTaskById(@RequestParam("id") int id, HttpSession session, Model model,
                               @RequestParam("creationDate") String creationDateString,
                               @RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("deadline") String deadlineString,
                               @RequestParam("taskStatusId") long taskStatusId,
                               @RequestParam("priority") short priority)
    {
        LOGGER.error("POST edit/" + id);
        if(session.getAttribute("user") == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Timestamp creationDate = Timestamp.valueOf(creationDateString);
        Timestamp deadline = Timestamp.valueOf(deadlineString);

        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        task.setCreationDate(creationDate);
        task.setDeadline(deadline);
        task.setPriority(priority);

        LOGGER.error(task);
        try {
            taskService.updateTask(task, taskStatusId);
            LOGGER.error("task updated");
            return showTaskById(id, session, model);
        } catch (Exception e)
        {
            LOGGER.error("exception");
            throw e;
        }
    }
}
