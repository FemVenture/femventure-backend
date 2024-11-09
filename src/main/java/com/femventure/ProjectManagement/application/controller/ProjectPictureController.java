package com.femventure.ProjectManagement.application.controller;

import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectPictureRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.ProjectPictureResponseDto;
import com.femventure.ProjectManagement.domain.interfaces.service.IProjectPictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Project Picture Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/project-pictures")
public class ProjectPictureController {

    private final IProjectPictureService projectPictureService;

    public ProjectPictureController(IProjectPictureService projectPictureService) {
        this.projectPictureService = projectPictureService;
    }

    @Operation(summary = "Add a new picture to a project")
    @PostMapping("/projects/{projectId}/pictures")
    public ResponseEntity<ProjectPictureResponseDto> addPictureToProject(@PathVariable Long projectId,
                                                                         @RequestBody ProjectPictureRequestDto projectPictureRequestDto) {
        ProjectPictureResponseDto response = projectPictureService.addPictureToProject(projectId, projectPictureRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all pictures for a specific project")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectPictureResponseDto>> getPicturesByProjectId(@PathVariable Long projectId) {
        List<ProjectPictureResponseDto> response = projectPictureService.getPicturesByProjectId(projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a picture by ID")
    @DeleteMapping("/{pictureId}")
    public ResponseEntity<Void> deletePictureById(@PathVariable Long pictureId) {
        projectPictureService.deletePictureById(pictureId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
