package com.application.model;

import jakarta.persistence.*;
@Table(name = "task_tag_task")
public class TaskTaskTag {
    //TODO: ???
    private final long taskId;
    private final long taskTagId;
    public long getTaskId() {
        return taskId;
    }
    public long getTaskTagId() { return taskTagId; }
    @Override
    public String toString() {
        return "TaskTaskTag{" +
                "taskId=" + taskId +
                ", taskTagId=" + taskTagId +
                '}';
    }
    TaskTaskTag(long taskId, long taskTagId)
    {
        this.taskId = taskId;
        this.taskTagId = taskTagId;
    }
}
