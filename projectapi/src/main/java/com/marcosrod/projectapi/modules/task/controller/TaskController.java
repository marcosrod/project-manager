package com.marcosrod.projectapi.modules.task.controller;

import com.marcosrod.projectapi.modules.task.dto.TaskRequest;
import com.marcosrod.projectapi.modules.task.dto.TaskResponse;
import com.marcosrod.projectapi.modules.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    @PostMapping
    public TaskResponse save(@RequestBody @Valid TaskRequest request) {
        return service.save(request);
    }

    @GetMapping("project/{projectId}")
    public Page<TaskResponse> findAllByProject(Pageable pageable, @PathVariable("projectId") Long projectId) {
        return service.findAllByProject(pageable, projectId);
    }
}
