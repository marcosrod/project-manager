package com.marcosrod.projectapi.modules.task.controller;

import com.marcosrod.projectapi.modules.task.service.TaskService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static com.marcosrod.projectapi.modules.common.helper.JsonHelper.asJsonString;
import static com.marcosrod.projectapi.modules.common.helper.PageHelper.getPageable;
import static com.marcosrod.projectapi.modules.project.helper.ProjectHelper.TEST_NUMBER_ONE;
import static com.marcosrod.projectapi.modules.task.helper.TaskHelper.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    private static final String API_URI = "/api/tasks";
    private static final String API_URI_BY_PROJECT = API_URI + "/project/" + TEST_NUMBER_ONE;

    @MockBean
    private TaskService service;
    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    void save_shouldReturnTaskResponse_whenCalled() {
        var taskResponse = getTaskResponse();
        var request = getTaskRequest();

        doReturn(taskResponse).when(service).save(request);

        mvc.perform(MockMvcRequestBuilders.post(API_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(taskResponse.id()))
                .andExpect(jsonPath("$.name").value(taskResponse.name()))
                .andExpect(jsonPath("$.description").value(taskResponse.description()))
                .andExpect(jsonPath("$.status").value(taskResponse.status()))
                .andExpect(jsonPath("$.projectId").value(taskResponse.projectId()))
                .andExpect(jsonPath("$.projectName").value(taskResponse.projectName()));

        verify(service).save(request);
    }

    @SneakyThrows
    @Test
    void findAllByProject_shouldReturnPageTaskResponse_whenCalled() {
        var pageable = getPageable();
        var taskResponse = getTaskResponse();
        var taskResponsePage = new PageImpl<>(Collections.singletonList(taskResponse), pageable, TEST_NUMBER_ONE);

        doReturn(taskResponsePage).when(service).findAllByProject(pageable, TEST_NUMBER_ONE);

        mvc.perform(MockMvcRequestBuilders.get(API_URI_BY_PROJECT)
                        .param("page", String.valueOf(pageable.getPageNumber()))
                        .param("size", String.valueOf(pageable.getPageSize())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(taskResponse.id()))
                .andExpect(jsonPath("$.content[0].name").value(taskResponse.name()))
                .andExpect(jsonPath("$.content[0].description").value(taskResponse.description()))
                .andExpect(jsonPath("$.content[0].status").value(taskResponse.status()))
                .andExpect(jsonPath("$.content[0].projectId").value(taskResponse.projectId()))
                .andExpect(jsonPath("$.content[0].projectName").value(taskResponse.projectName()));

        verify(service).findAllByProject(pageable, TEST_NUMBER_ONE);
    }

    @SneakyThrows
    @Test
    void findAllByProject_shouldReturnEmptyPage_whenNoTasksFound() {
        var pageable = getPageable();
        var emptyPage = new PageImpl<>(Collections.emptyList(), pageable, TEST_NUMBER_ONE);

        doReturn(emptyPage).when(service).findAllByProject(pageable, TEST_NUMBER_ONE);

        mvc.perform(MockMvcRequestBuilders.get(API_URI_BY_PROJECT)
                        .param("page", String.valueOf(pageable.getPageNumber()))
                        .param("size", String.valueOf(pageable.getPageSize())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isEmpty());

        verify(service).findAllByProject(pageable, TEST_NUMBER_ONE);
    }
}
