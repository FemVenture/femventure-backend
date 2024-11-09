package com.femventure.ProjectManagement.domain.interfaces.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectPictureRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.ProjectPictureResponseDto;

import java.util.List;

public interface IProjectPictureService {

    ProjectPictureResponseDto addPictureToProject(Long projectId, ProjectPictureRequestDto projectPictureRequestDto);

    List<ProjectPictureResponseDto> getPicturesByProjectId(Long projectId);

    void deletePictureById(Long pictureId);
}
