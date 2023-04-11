package com.application.model;

public class TaskUser {
    private Task task;
    private User user;
    private TaskRole taskRole;
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }
    public TaskRole getTaskRole() { return taskRole; }
    public void setTaskRole(TaskRole taskRole) { this.taskRole = taskRole; }

    @Override
    public String toString() {
        return "TaskUser{" +
                "task=" + task +
                ", user=" + user +
                ", taskRole=" + taskRole +
                '}';
    }

    public TaskUser(Task task, User user, TaskRole taskRole) {
        this.task = task;
        this.user = user;
        this.taskRole = taskRole;
    }
}
