package com.application.model;

import org.w3c.dom.ls.LSInput;

import java.util.List;

public class TaskTag {
    private long id;
    private String name;
    private String description;
    private List<Task> tasks;
    public long getId()
    {
        return id;
    }
    public void setId(long id) { this.id = id; }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

    @Override
    public String toString() {
        return "TaskTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public TaskTag(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
