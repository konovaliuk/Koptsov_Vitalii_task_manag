package com.application.model;

import java.util.List;

public class TaskRole {
    private long id;
    private String name;
    private List<Task> tasks;
    private List<User> users;
    public void setId(long id) { this.id = id; }
    public long getId() {
        return id;
    }
    public void setName(String name) { this.name = name; }
    public String getName() {
        return name;
    }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
    public List<Task> getTasks() { return tasks; }
    public void setUsers(List<User> users) { this.users = users; }
    public List<User> getUsers() { return users; }

    @Override
    public String toString() {
        return "TaskRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public TaskRole(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
