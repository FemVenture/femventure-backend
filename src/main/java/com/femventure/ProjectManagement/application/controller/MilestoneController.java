package com.femventure.ProjectManagement.application.controller;

import com.femventure.ProjectManagement.domain.dto.Project.request.MilestoneRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.MilestoneResponseDto;
import com.femventure.ProjectManagement.domain.interfaces.service.IMilestoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/milestones")
@Tag(name = "Milestone Controller")
public class MilestoneController {

    private final IMilestoneService milestoneService;

    public MilestoneController(IMilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @Operation(summary = "Create a new milestone for a specific project")
    @PostMapping("/project/{projectId}")
    public ResponseEntity<MilestoneResponseDto> createMilestone(@PathVariable Long projectId,
                                                                @RequestBody MilestoneRequestDto milestoneRequestDto) {
        MilestoneResponseDto response = milestoneService.createMilestone(projectId, milestoneRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a milestone by ID")
    @GetMapping("/{milestoneId}")
    public ResponseEntity<MilestoneResponseDto> getMilestoneById(@PathVariable Long milestoneId) {
        MilestoneResponseDto response = milestoneService.getMilestoneById(milestoneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all milestones for a specific project")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<MilestoneResponseDto>> getMilestonesByProject(@PathVariable Long projectId) {
        List<MilestoneResponseDto> response = milestoneService.getMilestonesByProjectId(projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update a milestone by ID")
    @PutMapping("/{milestoneId}")
    public ResponseEntity<MilestoneResponseDto> updateMilestone(@PathVariable Long milestoneId,
                                                                @RequestBody MilestoneRequestDto milestoneRequestDto) {
        MilestoneResponseDto response = milestoneService.updateMilestone(milestoneId, milestoneRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a milestone by ID")
    @DeleteMapping("/{milestoneId}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long milestoneId) {
        milestoneService.deleteMilestone(milestoneId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update a milestone status by ID")
    @PatchMapping("/{milestoneId}/status")
    public ResponseEntity<MilestoneResponseDto> updateMilestoneStatus(@PathVariable Long milestoneId) {
        MilestoneResponseDto response = milestoneService.updateMilestoneStatus(milestoneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}