package com.marcosrod.projectapi.modules.project.model;

import com.marcosrod.projectapi.modules.project.dto.ProjectRequest;
import com.marcosrod.projectapi.modules.project.enums.ProjectStatus;
import com.marcosrod.projectapi.modules.task.model.Task;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @SequenceGenerator(sequenceName = "project_id_seq", name = "project_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Column(name = "fk_client")
    private Long clientId;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    public Project() {
    }

    public Project(Long id) {
        this.id = id;
    }

    public Project(Long id, String name, String description, ProjectStatus status, Long clientId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.clientId = clientId;
    }

    public Project(String name, String description, ProjectStatus status, Long clientId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.clientId = clientId;
    }

    public static Project of(ProjectRequest request) {
        return new Project(request.name(), request.description(),
                ProjectStatus.O, request.clientId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(name, project.name)
                && Objects.equals(description, project.description) && status == project.status
                && Objects.equals(clientId, project.clientId) && Objects.equals(tasks, project.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, clientId, tasks);
    }
}
