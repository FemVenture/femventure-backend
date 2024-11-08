package com.femventure.ProjectManagement.application.controller;

import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.ProjectResponseDto;
import com.femventure.ProjectManagement.domain.interfaces.service.IProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Project Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Create a new project")
    @PostMapping("/")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto projectRequestDto) {
        ProjectResponseDto response = projectService.createProject(projectRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a project by ID")
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long projectId) {
        ProjectResponseDto response = projectService.getProjectById(projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all projects by a specific creator ID")
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<ProjectResponseDto>> getProjectsByCreator(@PathVariable Long creatorId) {
        List<ProjectResponseDto> response = projectService.getProjectsByCreator(creatorId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update a project by ID")
    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long projectId,
                                                            @RequestBody ProjectRequestDto projectRequestDto) {
        ProjectResponseDto response = projectService.updateProject(projectId, projectRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a project by ID")
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
