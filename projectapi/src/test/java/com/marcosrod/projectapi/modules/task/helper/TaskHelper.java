package com.marcosrod.projectapi.modules.task.helper;

import com.marcosrod.projectapi.modules.task.dto.TaskRequest;
import com.marcosrod.projectapi.modules.task.dto.TaskResponse;
import com.marcosrod.projectapi.modules.task.model.Task;

import static com.marcosrod.projectapi.modules.project.helper.ProjectHelper.*;
import static com.marcosrod.projectapi.modules.task.enums.TaskStatus.*;

public class TaskHelper {

    private static final String TEST_NAME = "testTask";
    private static final String TEST_DESCRIPTION = "testDescription";

    public static TaskRequest getTaskRequest() {
        return new TaskRequest(TEST_NAME, TEST_DESCRIPTION, TEST_NUMBER_ONE);
    }

    public static Task getTask() {
        return new Task(TEST_NAME, TEST_DESCRIPTION, C, getSavedProject());
    }

    public static Task getSavedTask() {
        return new Task(TEST_NUMBER_ONE, TEST_NAME, TEST_DESCRIPTION, C, getProject());
    }

    public static TaskResponse getTaskResponse() {
        return new TaskResponse(TEST_NUMBER_ONE, TEST_NAME, TEST_DESCRIPTION, C.getDescription(),
                TEST_NUMBER_ONE, getProject().getName());
    }
}
