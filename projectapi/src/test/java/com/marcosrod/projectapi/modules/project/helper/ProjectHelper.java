package com.marcosrod.projectapi.modules.project.helper;

import com.marcosrod.projectapi.modules.project.dto.ProjectRequest;
import com.marcosrod.projectapi.modules.project.dto.ProjectResponse;
import com.marcosrod.projectapi.modules.project.model.Project;

import static com.marcosrod.projectapi.modules.project.enums.ProjectStatus.*;

public class ProjectHelper {

    private static final String TEST_NAME = "testProject";
    private static final String TEST_DESCRIPTION = "testDescription";
    public static final Long TEST_NUMBER_ONE = 1L;

    public static ProjectRequest getProjectRequest() {
        return new ProjectRequest(TEST_NAME, TEST_DESCRIPTION, TEST_NUMBER_ONE);
    }

    public static Project getProject() {
        return new Project(TEST_NAME, TEST_DESCRIPTION, O, TEST_NUMBER_ONE);
    }

    public static Project getSavedProject() {
        return new Project(TEST_NUMBER_ONE, TEST_NAME, TEST_DESCRIPTION, O, TEST_NUMBER_ONE);
    }

    public static ProjectResponse getProjectResponse() {
        return new ProjectResponse(TEST_NUMBER_ONE, TEST_NAME, TEST_DESCRIPTION,
                O.getDescription(), TEST_NUMBER_ONE);
    }
}
