package com.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TaskUserId {
    @Column(name = "task_id")
    private Long taskId;
    @Column(name = "user_id")
    private Long userId;
}
