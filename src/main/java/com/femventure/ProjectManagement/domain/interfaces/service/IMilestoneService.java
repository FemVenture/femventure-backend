package com.femventure.ProjectManagement.domain.interfaces.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.MilestoneRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.MilestoneResponseDto;

import java.util.List;

public interface IMilestoneService {
    MilestoneResponseDto createMilestone(Long projectId, MilestoneRequestDto requestDto);
     MilestoneResponseDto updateMilestone(Long milestoneId, MilestoneRequestDto requestDto); // Actualizar un hito
    void deleteMilestone(Long milestoneId); // Eliminar un hito

}
