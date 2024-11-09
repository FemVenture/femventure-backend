package com.femventure.ProjectManagement.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectPictureRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.ProjectPictureResponseDto;
import com.femventure.ProjectManagement.domain.entity.Project;
import com.femventure.ProjectManagement.domain.entity.ProjectPicture;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectPictureRepository;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectRepository;
import com.femventure.ProjectManagement.domain.interfaces.service.IProjectPictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectPictureServiceImpl implements IProjectPictureService {


    private  final IProjectPictureRepository projectPictureRepository;

    private final IProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectPictureServiceImpl(IProjectPictureRepository projectPictureRepository, IProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectPictureRepository = projectPictureRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ProjectPictureResponseDto addPictureToProject(Long projectId, ProjectPictureRequestDto projectPictureRequestDto) {
        projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        ProjectPicture projectPicture = new ProjectPicture(projectPictureRequestDto.getImageUrl(), projectId);
        projectPictureRepository.save(projectPicture);
       return modelMapper.map(projectPicture, ProjectPictureResponseDto.class);
    }

    @Override
    public List<ProjectPictureResponseDto> getPicturesByProjectId(Long projectId) {
        List<ProjectPicture> projectPictures = projectPictureRepository.findByProjectId(projectId);
      return projectPictures.stream().map(projectPicture -> modelMapper.map(projectPicture, ProjectPictureResponseDto.class)).collect(Collectors.toList());
    }



    @Override
    public void deletePictureById(Long pictureId) {
        if (!projectPictureRepository.existsById(pictureId)) {
            throw new RuntimeException("Project picture not found");
        }
        projectPictureRepository.deleteById(pictureId);
    }
}

