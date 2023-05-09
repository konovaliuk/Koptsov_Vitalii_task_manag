package com.application.model;

import java.sql.Timestamp;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Column(name = "deadline_date")
    private Timestamp deadline;
    @ManyToOne
    @JoinColumn(name = "task_status_id", nullable=false)
    private TaskStatus status;
    private short priority;
    @OneToMany(mappedBy="task")
    private List<TaskUser> taskUsers;
    @ManyToMany
    @JoinTable(
            name = "task_tag_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "task_tag_id"))
    private List<TaskTag> tags;
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
}
