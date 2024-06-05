package com.marcosrod.projectapi.modules.task.service;

import com.marcosrod.projectapi.modules.common.exception.ValidationException;
import com.marcosrod.projectapi.modules.project.service.ProjectService;
import com.marcosrod.projectapi.modules.task.dto.TaskRequest;
import com.marcosrod.projectapi.modules.task.dto.TaskResponse;
import com.marcosrod.projectapi.modules.task.model.Task;
import com.marcosrod.projectapi.modules.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.marcosrod.projectapi.modules.task.enums.TaskError.DUPLICATED_NAME;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository repository;
    private final ProjectService projectService;

    public TaskResponse save(TaskRequest request) {
        validateDuplicatedName(request.name());

        var project = projectService.findById(request.projectId());
        var savedTask = repository.save(Task.of(request, project));

        return new TaskResponse(savedTask.getId(), savedTask.getName(), savedTask.getDescription(),
                savedTask.getStatus().getDescription(), project.getId(), project.getName());
    }

    public Page<TaskResponse> findAllByProject(Pageable pageable, Long projectId) {
        var project = projectService.findById(projectId);
        var projectName = project.getName();

        return repository.findAllByProject(pageable, project)
                .map(task -> convertToResponse(task, projectId, projectName));
    }

    private TaskResponse convertToResponse(Task task, Long projectId, String projectName) {
        return new TaskResponse(task.getId(), task.getName(), task.getDescription(),
                task.getStatus().getDescription(), projectId, projectName);
    }

    private void validateDuplicatedName(String name) {
        if (repository.existsByName(name)) {
            throw new ValidationException(DUPLICATED_NAME.getErrorMessage());
        }
    }

}
