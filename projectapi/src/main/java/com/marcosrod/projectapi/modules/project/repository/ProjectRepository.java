package com.marcosrod.projectapi.modules.project.repository;

import com.marcosrod.projectapi.modules.project.enums.ProjectStatus;
import com.marcosrod.projectapi.modules.project.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByName(String name);
    Page<Project> findByStatus(ProjectStatus status, Pageable pageable);
}
