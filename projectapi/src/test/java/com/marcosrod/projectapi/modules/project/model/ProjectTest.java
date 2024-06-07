package com.marcosrod.projectapi.modules.project.model;

import org.junit.jupiter.api.Test;

import static com.marcosrod.projectapi.modules.project.helper.ProjectHelper.getProjectRequest;
import static com.marcosrod.projectapi.modules.project.helper.ProjectHelper.getProject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTest {

    @Test
    public void of_shouldReturnProject_whenCalled() {
        var project = Project.of(getProjectRequest());

        assertEquals(project, getProject());
    }
}
