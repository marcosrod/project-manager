package com.marcosrod.projectapi.modules.task.model;

import org.junit.jupiter.api.Test;

import static com.marcosrod.projectapi.modules.project.helper.ProjectHelper.getSavedProject;
import static com.marcosrod.projectapi.modules.task.helper.TaskHelper.getTask;
import static com.marcosrod.projectapi.modules.task.helper.TaskHelper.getTaskRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void of_shouldReturnTask_whenCalled() {
        var task = Task.of(getTaskRequest(), getSavedProject());

        assertEquals(task, getTask());
    }
}
