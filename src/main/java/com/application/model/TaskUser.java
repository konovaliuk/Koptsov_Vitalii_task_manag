package com.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_user")
public class TaskUser {
    @EmbeddedId
    private TaskUserId id;
    @ManyToOne
    @JoinColumn(name = "task_role_id")
    private TaskRole taskRole;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    Task task;
    @Override
    public String toString() {
        return "TaskUser{" +
                "taskId=" + id.getTaskId() +
                ", userId=" + id.getUserId() +
                ", taskRole=" + taskRole +
                '}';
    }
}
