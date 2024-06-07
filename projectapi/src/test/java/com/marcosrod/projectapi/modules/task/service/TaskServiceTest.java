package com.marcosrod.projectapi.modules.task.service;

import com.marcosrod.projectapi.modules.common.exception.ValidationException;
import com.marcosrod.projectapi.modules.project.enums.ProjectError;
import com.marcosrod.projectapi.modules.project.service.ProjectService;
import com.marcosrod.projectapi.modules.task.enums.TaskError;
import com.marcosrod.projectapi.modules.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static com.marcosrod.projectapi.modules.common.helper.PageHelper.getPageable;
import static com.marcosrod.projectapi.modules.project.enums.ProjectError.ID_NOT_FOUND;
import static com.marcosrod.projectapi.modules.project.helper.ProjectHelper.*;
import static com.marcosrod.projectapi.modules.project.helper.ProjectHelper.TEST_NUMBER_ONE;
import static com.marcosrod.projectapi.modules.task.helper.TaskHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService service;
    @Mock
    private TaskRepository repository;
    @Mock
    private ProjectService projectService;

    @Test
    void save_shouldReturnTaskResponse_whenValidRequest() {
        var request = getTaskRequest();
        var requestName = request.name();
        var task = getTask();

        doReturn(false).when(repository).existsByName(requestName);
        doReturn(getSavedProject()).when(projectService).findById(TEST_NUMBER_ONE);
        doReturn(getSavedTask()).when(repository).save(task);

        assertThat(service.save(request))
                .isEqualTo(getTaskResponse());

        verify(repository).existsByName(requestName);
        verify(projectService).findById(TEST_NUMBER_ONE);
        verify(repository).save(task);
    }

    @Test
    void save_shouldThrowException_whenDuplicatedName() {
        var request = getTaskRequest();
        var requestName = request.name();

        doReturn(true).when(repository).existsByName(requestName);

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> service.save(request))
                .withMessage(TaskError.DUPLICATED_NAME.getErrorMessage());

        verify(repository).existsByName(requestName);
        verify(projectService, never()).findById(anyLong());
        verify(repository, never()).save(any());
    }

    @Test
    void save_shouldThrowException_whenInvalidProjectId() {
        var request = getTaskRequest();
        var requestName = request.name();

        doReturn(false).when(repository).existsByName(requestName);
        doThrow(new ValidationException(ID_NOT_FOUND.getErrorMessage()))
                .when(projectService).findById(TEST_NUMBER_ONE);

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> service.save(request))
                .withMessage(ID_NOT_FOUND.getErrorMessage());

        verify(repository).existsByName(requestName);
        verify(projectService).findById(TEST_NUMBER_ONE);
        verify(repository, never()).save(any());
    }

    @Test
    void findAllByProject_shouldReturnPageTaskResponse_whenCalled() {
        var savedTask = getSavedTask();
        var pageable = getPageable();
        var project = getSavedProject();

        var taskPage = new PageImpl<>(Collections.singletonList(savedTask), pageable, TEST_NUMBER_ONE);
        var taskResponsePage = new PageImpl<>(Collections.singletonList(getTaskResponse()), pageable, TEST_NUMBER_ONE);

        doReturn(taskPage).when(repository).findAllByProject(pageable, project);
        doReturn(project).when(projectService).findById(TEST_NUMBER_ONE);

        assertThat(service.findAllByProject(pageable, TEST_NUMBER_ONE))
                .isEqualTo(taskResponsePage);

        verify(repository).findAllByProject(pageable, project);
    }

    @Test
    void findAllByProject_shouldReturnEmptyPage_whenNoTasksFound() {
        var pageable = getPageable();
        var project = getSavedProject();

        var emptyPage = new PageImpl<>(Collections.emptyList(), pageable, TEST_NUMBER_ONE);

        doReturn(emptyPage).when(repository).findAllByProject(pageable, project);
        doReturn(project).when(projectService).findById(TEST_NUMBER_ONE);

        assertThat(service.findAllByProject(pageable, TEST_NUMBER_ONE))
                .isEqualTo(emptyPage);

        verify(repository).findAllByProject(pageable, project);
    }


}
