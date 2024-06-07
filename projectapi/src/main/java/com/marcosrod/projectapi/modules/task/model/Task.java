package com.marcosrod.projectapi.modules.task.model;

import com.marcosrod.projectapi.modules.project.model.Project;
import com.marcosrod.projectapi.modules.task.dto.TaskRequest;
import com.marcosrod.projectapi.modules.task.enums.TaskStatus;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @SequenceGenerator(sequenceName = "task_id_seq", name = "task_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project")
    private Project project;

    public Task() {
    }

    public Task(Long id, String name, String description, TaskStatus status, Project project) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.project = project;
    }

    public Task(String name, String description, TaskStatus status, Project project) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.project = project;
    }

    public static Task of(TaskRequest request, Project project) {
        return new Task(request.name(), request.description(), TaskStatus.C, project);
    }

    public Long getId() {
        return id;
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

    public TaskStatus getStatus() {
        return status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status && Objects.equals(project, task.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, project);
    }
}
