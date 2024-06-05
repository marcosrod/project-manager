package com.marcosrod.projectapi.modules.task.repository;

import com.marcosrod.projectapi.modules.project.model.Project;
import com.marcosrod.projectapi.modules.task.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByName(String name);
    Page<Task> findAllByProject(Pageable pageable, Project project);
}
