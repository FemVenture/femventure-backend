package com.femventure.ProjectManagement.service;

import com.femventure.ProjectManagement.application.validation.ProjectValidation;
import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectUpdateRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.MilestoneResponseDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.ProjectResponseDto;
import com.femventure.ProjectManagement.domain.entity.Milestone;
import com.femventure.ProjectManagement.domain.entity.Project;
import com.femventure.ProjectManagement.domain.interfaces.repository.IMilestoneRepository;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectRepository;
import com.femventure.ProjectManagement.domain.interfaces.service.IProjectService;
import com.femventure.ProjectManagement.shared.MilestoneStatus;
import com.femventure.UsersManagement.application.validation.MentorValidation;
import com.femventure.UsersManagement.domain.entity.Entrepreneur;
import com.femventure.UsersManagement.domain.interfaces.repository.IEntrepreneurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements IProjectService {


    private final IProjectRepository projectRepository;
    private final IEntrepreneurRepository entrepreneurRepository;
    private final IMilestoneRepository milestoneRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(IProjectRepository projectRepository, IMilestoneRepository milestoneRepository, IEntrepreneurRepository entrepreneurRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.entrepreneurRepository = entrepreneurRepository;
        this.modelMapper = modelMapper;
        this.milestoneRepository = milestoneRepository;
    }


    @Override
    public ProjectResponseDto createProject(Long entrepreneurId, ProjectRequestDto projectRequestDto) {

        ProjectValidation.validateProject(projectRequestDto);

        entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new RuntimeException("Entrepreneur not found"));

        // Crear y guardar el proyecto para obtener su ID
        var project = new Project(projectRequestDto, entrepreneurId);
        var savedProject = projectRepository.save(project);

        // Ahora que el proyecto tiene un ID, creamos el milestone
        var milestone = new Milestone();
        milestone.setProjectId(savedProject.getId());
        milestone.setStatus(MilestoneStatus.PENDING);
        milestone.setTitle("Esperando aprobación");

        // Guardar el milestone
        var savedMilestone = milestoneRepository.save(milestone);

        // Asociar el milestone al proyecto y actualizar
        savedProject.setCurrentMilestoneId(savedMilestone.getId());
        var updatedProject = projectRepository.save(savedProject);

        return modelMapper.map(updatedProject, ProjectResponseDto.class);
    }


    public void updateProjectFundingStats(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<Milestone> milestones = milestoneRepository.findByProjectId(projectId);


        double totalFundingGoal = milestones.stream()
                .mapToDouble(Milestone::getFundingGoal)
                .sum();

        double totalFundsRaised = milestones.stream()
                .mapToDouble(Milestone::getFundsRaised)
                .sum();

        project.setTotalFundingGoal(totalFundingGoal);
        project.setFundsRaised(totalFundsRaised);

        projectRepository.save(project);
    }

    // Otro

    @Override
    public ProjectResponseDto getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return modelMapper.map(project, ProjectResponseDto.class);
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream()
                .map(project -> modelMapper.map(project, ProjectResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MilestoneResponseDto> getMilestonesByProjectId(Long projectId) {
        projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        var milestones = milestoneRepository.findByProjectId(projectId);
        return milestones.stream()
                .map(milestone -> modelMapper.map(milestone, MilestoneResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDto updateProject(Long projectId, ProjectUpdateRequestDto projectRequestDto) {
        var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.update(projectRequestDto);
        projectRepository.save(project);
        return modelMapper.map(project, ProjectResponseDto.class);
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<ProjectResponseDto> getProjectsByEntrepreneurId(Long entrepreneurId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new RuntimeException("Entrepreneur not found"));

        List<Project> projects = projectRepository.findByEntrepreneurId(entrepreneurId);

      return projects.stream().map(project -> modelMapper.map(project, ProjectResponseDto.class)).collect(Collectors.toList());
    }
}
