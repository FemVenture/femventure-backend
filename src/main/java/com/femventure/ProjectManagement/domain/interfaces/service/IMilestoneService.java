package com.femventure.ProjectManagement.domain.interfaces.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.MilestoneRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.MilestoneResponseDto;

import java.util.List;

public interface IMilestoneService {
    MilestoneResponseDto createMilestone(Long projectId, MilestoneRequestDto milestoneRequestDto);
    MilestoneResponseDto getMilestoneById(Long milestoneId);
    List<MilestoneResponseDto> getMilestonesByProjectId(Long projectId);
    MilestoneResponseDto updateMilestone(Long milestoneId, MilestoneRequestDto milestoneRequestDto);
    void deleteMilestone(Long milestoneId);
    MilestoneResponseDto updateMilestoneStatus(Long milestoneId);
}
