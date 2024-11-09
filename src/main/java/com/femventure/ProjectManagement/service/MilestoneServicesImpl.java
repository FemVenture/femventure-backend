package com.femventure.ProjectManagement.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.MilestoneRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.MilestoneResponseDto;
import com.femventure.ProjectManagement.domain.entity.Milestone;
import com.femventure.ProjectManagement.domain.entity.Project;
import com.femventure.ProjectManagement.domain.interfaces.repository.IMilestoneRepository;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectRepository;
import com.femventure.ProjectManagement.domain.interfaces.service.IMilestoneService;
import com.femventure.ProjectManagement.domain.interfaces.service.IProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MilestoneServicesImpl implements IMilestoneService {

    private final IMilestoneRepository milestoneRepository;


    private final IProjectRepository projectRepository;
    private final IProjectService projectService;
    private final ModelMapper modelMapper;

    public MilestoneServicesImpl(IMilestoneRepository milestoneRepository, IProjectRepository projectRepository, IProjectService projectService,  ModelMapper modelMapper) {
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
        this.modelMapper = modelMapper;

    }

    @Override
    public MilestoneResponseDto createMilestone(Long projectId, MilestoneRequestDto milestoneRequestDto) {
       var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        long currentMilestonesCount = milestoneRepository.countByProjectId(projectId);
        if (currentMilestonesCount >= project.getTotalMilestones()) {
            throw new RuntimeException("Cannot create more milestones than the specified total.");
        }

        var milestone = new Milestone(milestoneRequestDto, projectId);
        milestoneRepository.save(milestone);

        projectService.updateProjectFundingStats(projectId);
        return  modelMapper.map(milestone, MilestoneResponseDto.class);

    }

    @Override
    public MilestoneResponseDto getMilestoneById(Long milestoneId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));
        return modelMapper.map(milestone, MilestoneResponseDto.class);

    }

    @Override
    public List<MilestoneResponseDto> getMilestonesByProjectId(Long projectId) {
        List<Milestone> milestones = milestoneRepository.findByProjectId(projectId);

        return milestones.stream()
                .map(milestone -> modelMapper.map(milestone, MilestoneResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MilestoneResponseDto updateMilestone(Long milestoneId, MilestoneRequestDto milestoneRequestDto) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));


        milestone.update(milestoneRequestDto);

         milestoneRepository.save(milestone);
        projectService.updateProjectFundingStats(milestone.getProjectId());
        return modelMapper.map(milestone, MilestoneResponseDto.class);
    }

    @Override
    public void deleteMilestone(Long milestoneId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));

        milestoneRepository.delete(milestone);
        projectService.updateProjectFundingStats(milestone.getProjectId());
    }
}
