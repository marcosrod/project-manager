package com.marcosrod.projectapi.modules.project.controller;

import com.marcosrod.projectapi.modules.project.service.ProjectService;
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
import static com.marcosrod.projectapi.modules.project.helper.ProjectHelper.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    private static final String API_URI = "/api/projects";

    @MockBean
    private ProjectService service;
    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    void save_shouldReturnProjectResponse_whenCalled() {
        var request = getProjectRequest();
        var response = getProjectResponse();

        doReturn(response).when(service).save(request);

        mvc.perform(MockMvcRequestBuilders.post(API_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.name").value(response.name()))
                .andExpect(jsonPath("$.description").value(response.description()))
                .andExpect(jsonPath("$.status").value(response.status()))
                .andExpect(jsonPath("$.clientId").value(response.clientId()));

        verify(service).save(request);
    }

    @SneakyThrows
    @Test
    void findAllOpened_shouldReturnPageProjectResponse_whenCalled() {
        var response = getProjectResponse();
        var pageable = getPageable();
        var responsePage = new PageImpl<>(Collections.singletonList(response), pageable, TEST_NUMBER_ONE);

        doReturn(responsePage).when(service).findAllOpened(pageable);

        mvc.perform(MockMvcRequestBuilders.get(API_URI + "/open")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(response.id()))
                .andExpect(jsonPath("$.content[0].name").value(response.name()))
                .andExpect(jsonPath("$.content[0].description").value(response.description()))
                .andExpect(jsonPath("$.content[0].status").value(response.status()))
                .andExpect(jsonPath("$.content[0].clientId").value(response.clientId()));

        verify(service).findAllOpened(pageable);
    }

    @SneakyThrows
    @Test
    void findAllOpened_shouldReturnEmptyPage_whenNoOpenProjects() {
        var pageable = getPageable();
        var emptyPage = new PageImpl<>(Collections.emptyList(), pageable, TEST_NUMBER_ONE);

        doReturn(emptyPage).when(service).findAllOpened(pageable);

        mvc.perform(MockMvcRequestBuilders.get(API_URI + "/open")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isEmpty());

        verify(service).findAllOpened(pageable);
    }
}
