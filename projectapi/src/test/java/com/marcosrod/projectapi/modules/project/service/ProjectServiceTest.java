package com.marcosrod.projectapi.modules.project.service;

import com.marcosrod.projectapi.modules.client.service.ClientService;
import com.marcosrod.projectapi.modules.common.exception.ValidationException;
import com.marcosrod.projectapi.modules.project.enums.ProjectError;
import com.marcosrod.projectapi.modules.project.enums.ProjectStatus;
import com.marcosrod.projectapi.modules.project.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.Optional;

import static com.marcosrod.projectapi.modules.common.helper.PageHelper.getPageable;
import static com.marcosrod.projectapi.modules.project.helper.ProjectHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @InjectMocks
    private ProjectService service;
    @Mock
    private ProjectRepository repository;
    @Mock
    private ClientService clientService;

    @Test
    void save_shouldReturnProjectResponse_whenValidRequest() {
        var request = getProjectRequest();
        var requestName = request.name();
        var project = getProject();

        doReturn(false).when(repository).existsByName(requestName);
        doReturn(true).when(clientService).existsById(TEST_NUMBER_ONE);
        doReturn(getSavedProject()).when(repository).save(project);

        assertThat(service.save(request))
                .isEqualTo(getProjectResponse());

        verify(repository).existsByName(requestName);
        verify(clientService).existsById(TEST_NUMBER_ONE);
        verify(repository).save(project);
    }

    @Test
    void save_shouldThrowException_whenDuplicatedName() {
        var request = getProjectRequest();
        var requestName = request.name();

        doReturn(true).when(repository).existsByName(requestName);

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> service.save(request))
                .withMessage(ProjectError.DUPLICATED_NAME.getErrorMessage());

        verify(repository).existsByName(requestName);
        verify(clientService, never()).existsById(anyLong());
        verify(repository, never()).save(any());
    }

    @Test
    void save_shouldThrowException_whenInvalidClientId() {
        var request = getProjectRequest();
        var requestName = request.name();

        doReturn(false).when(repository).existsByName(requestName);
        doReturn(false).when(clientService).existsById(TEST_NUMBER_ONE);

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> service.save(request))
                .withMessage(ProjectError.INVALID_CLIENT.getErrorMessage());

        verify(repository).existsByName(requestName);
        verify(clientService).existsById(TEST_NUMBER_ONE);
        verify(repository, never()).save(any());
    }

    @Test
    void findById_shouldReturnProject_whenValidId() {
        var savedProject = getSavedProject();

        doReturn(Optional.of(savedProject)).when(repository).findById(TEST_NUMBER_ONE);

        assertThat(service.findById(TEST_NUMBER_ONE))
                .isEqualTo(savedProject);

        verify(repository).findById(TEST_NUMBER_ONE);
    }

    @Test
    void findById_shouldThrowException_whenInvalidId() {
        doReturn(Optional.empty()).when(repository).findById(TEST_NUMBER_ONE);

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> service.findById(TEST_NUMBER_ONE))
                        .withMessage(ProjectError.ID_NOT_FOUND.getErrorMessage());

        verify(repository).findById(TEST_NUMBER_ONE);
    }

    @Test
    void findAllOpened_shouldReturnPageProjectResponse_whenCalled() {
        var pageable = getPageable();
        var savedProject = getSavedProject();
        var openStatus = ProjectStatus.O;
        var projectPage = new PageImpl<>(Collections.singletonList(savedProject), pageable, TEST_NUMBER_ONE);
        var projectResponsePage = new PageImpl<>(Collections.singletonList(getProjectResponse()), pageable, TEST_NUMBER_ONE);

        doReturn(projectPage).when(repository).findByStatus(openStatus, pageable);

        assertThat(service.findAllOpened(pageable))
                .isEqualTo(projectResponsePage);

        verify(repository).findByStatus(openStatus, pageable);
    }

    @Test
    void findAllOpened_shouldReturnEmptyPage_whenNoOpenProjects() {
        var pageable = getPageable();
        var openStatus = ProjectStatus.O;
        var projectPage = new PageImpl<>(Collections.emptyList(), pageable, TEST_NUMBER_ONE);

        doReturn(projectPage).when(repository).findByStatus(openStatus, pageable);

        assertThat(service.findAllOpened(pageable))
                .isEqualTo(projectPage);

        verify(repository).findByStatus(openStatus, pageable);
    }
}
