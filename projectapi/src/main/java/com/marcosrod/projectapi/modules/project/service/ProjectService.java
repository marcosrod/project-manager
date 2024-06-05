package com.marcosrod.projectapi.modules.project.service;

import com.marcosrod.projectapi.modules.client.service.ClientService;
import com.marcosrod.projectapi.modules.common.exception.ValidationException;
import com.marcosrod.projectapi.modules.project.dto.ProjectRequest;
import com.marcosrod.projectapi.modules.project.dto.ProjectResponse;
import com.marcosrod.projectapi.modules.project.enums.ProjectStatus;
import com.marcosrod.projectapi.modules.project.model.Project;
import com.marcosrod.projectapi.modules.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.marcosrod.projectapi.modules.project.enums.ProjectError.*;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ClientService clientService;

    public ProjectResponse save(ProjectRequest request) {
        validateDuplicatedName(request.name());
        validateClientId(request.clientId());

        var savedProject = repository.save(Project.of(request));

        return new ProjectResponse(savedProject.getId(), savedProject.getName(), savedProject.getDescription(),
                savedProject.getStatus().getDescription(), savedProject.getClientId());
    }

    public Page<ProjectResponse> findAllOpened(Pageable pageable) {
        return repository.findByStatus(ProjectStatus.O, pageable)
                .map(this::convertToResponse);
    }

    private ProjectResponse convertToResponse(Project project) {
        return new ProjectResponse(project.getId(), project.getName(), project.getDescription(),
                project.getStatus().getDescription(), project.getClientId());
    }

    public Project findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException(ID_NOT_FOUND.getErrorMessage()));
    }

    public void existsById(Long id) {
        if (!repository.existsById(id)) {
            throw new ValidationException(ID_NOT_FOUND.getErrorMessage());
        }
    }

    private void validateClientId(Long clientId) {
        if (!clientService.existsById(clientId)) {
            throw new ValidationException(INVALID_CLIENT.getErrorMessage());
        }
    }

    private void validateDuplicatedName(String name) {
        if (repository.existsByName(name)) {
            throw new ValidationException(DUPLICATED_NAME.getErrorMessage());
        }
    }


}
