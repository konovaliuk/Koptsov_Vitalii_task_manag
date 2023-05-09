package com.application.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_role")
public class TaskRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany
    private List<TaskUser> tasks;
    @Override
    public String toString() {
        return "TaskRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
