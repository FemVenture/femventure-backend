package com.femventure.ProjectManagement.domain.interfaces.service;

import ch.qos.logback.core.model.INamedModel;
import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.ProjectResponseDto;
import com.femventure.ProjectManagement.domain.entity.Project;

import java.util.List;

public interface IProjectService {

    ProjectResponseDto createProject(ProjectRequestDto requestDto);
    ProjectResponseDto getProjectById(Long projectId);
    List<ProjectResponseDto> getProjectsByCreator(Long creatorId);
    ProjectResponseDto updateProject(Long projectId, ProjectRequestDto requestDto);
    void deleteProject(Long projectId);
}
