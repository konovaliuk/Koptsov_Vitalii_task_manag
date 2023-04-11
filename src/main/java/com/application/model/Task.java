package com.application.model;

import java.time.Instant;
import java.util.List;

public class Task {
    private long id;
    private String name;
    private String description;
    private Instant creationDate;
    private Instant deadline;
    private TaskStatus status;
    private short priority;
    private List<TaskUser> taskUsers;
    private List<TaskTag> tags;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Instant getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }
    public Instant getDeadline() {
        return deadline;
    }
    public void setDeadline(Instant deadline) {
        this.deadline = deadline;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    public short getPriority() {
        return priority;
    }
    public void setPriority(short priority) {
        this.priority = priority;
    }
    public List<TaskUser> getTaskUsers() {
        return taskUsers;
    }
    public void setTaskUsers(List<TaskUser> taskUsers) {
        this.taskUsers = taskUsers;
    }
    public List<TaskTag> getTags() {
        return tags;
    }
    public void setTags(List<TaskTag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", deadline=" + deadline +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }
    public Task(long id, String name, String description, Instant creationDate, Instant deadline, TaskStatus status, short priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
    }
}
