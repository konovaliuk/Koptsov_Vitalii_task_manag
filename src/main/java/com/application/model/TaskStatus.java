package com.application.model;

import java.util.List;

public class TaskStatus {
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
        return "TaskStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public TaskStatus(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
