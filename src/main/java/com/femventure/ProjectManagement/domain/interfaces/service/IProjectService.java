package com.femventure.ProjectManagement.domain.interfaces.service;

import ch.qos.logback.core.model.INamedModel;
import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.MilestoneResponseDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.ProjectResponseDto;
import com.femventure.ProjectManagement.domain.entity.Project;

import java.util.List;

public interface IProjectService {

    ProjectResponseDto createProject(Long entrepreneurId,ProjectRequestDto projectRequestDto);

    ProjectResponseDto getProjectById(Long projectId);

    List<ProjectResponseDto> getAllProjects();
    void updateProjectFundingStats(Long projectId);

    List<MilestoneResponseDto> getMilestonesByProjectId(Long projectId);

    ProjectResponseDto updateProject(Long projectId, ProjectRequestDto projectRequestDto);

    void deleteProject(Long projectId);

    List<ProjectResponseDto> getProjectsByEntrepreneurId(Long entrepreneurId);
}
