package com.femventure.ProjectManagement.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.ProjectResponseDto;
import com.femventure.ProjectManagement.domain.entity.Project;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectRepository;
import com.femventure.ProjectManagement.domain.interfaces.service.IProjectService;
import com.femventure.ProjectManagement.shared.ProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements IProjectService {

    private final IProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(IProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public ProjectResponseDto createProject(ProjectRequestDto requestDto) {
        var existingProject = projectRepository.findByTitle(requestDto.getTitle());
        if (existingProject != null) {
            throw new RuntimeException("Project with this title already exists");
        }
        Project project = projectMapper.toEntity(requestDto);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toResponseDto(savedProject);
    }

    @Override
    public ProjectResponseDto getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
        return projectMapper.toResponseDto(project);
    }

    @Override
    public List<ProjectResponseDto> getProjectsByCreator(Long creatorId) {
        // Lógica para obtener proyectos por creador
        return projectRepository.findByCreatorId(creatorId)
                .stream()
                .map(projectMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProjectResponseDto updateProject(Long projectId, ProjectRequestDto requestDto) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));

        existingProject.setTitle(requestDto.getTitle());
        existingProject.setDescription(requestDto.getDescription());
        existingProject.setTotalFundingGoal(requestDto.getTotalFundingGoal());
        existingProject.setTag(requestDto.getTag());

        Project updatedProject = projectRepository.save(existingProject);
        return projectMapper.toResponseDto(updatedProject);
    }

    @Override
    @Transactional
    public void deleteProject(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new RuntimeException("Project not found with ID: " + projectId);
        }
        projectRepository.deleteById(projectId);
    }
}
