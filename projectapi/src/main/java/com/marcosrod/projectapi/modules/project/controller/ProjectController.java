package com.marcosrod.projectapi.modules.project.controller;

import com.marcosrod.projectapi.modules.project.dto.ProjectRequest;
import com.marcosrod.projectapi.modules.project.dto.ProjectResponse;
import com.marcosrod.projectapi.modules.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;

    @PostMapping
    public ProjectResponse save(@RequestBody @Valid ProjectRequest request) {
        return service.save(request);
    }

    @GetMapping("/open")
    public Page<ProjectResponse> findAllOpened(Pageable pageable) {
        return service.findAllOpened(pageable);
    }

}
