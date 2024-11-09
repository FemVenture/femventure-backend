package com.femventure.ProjectManagement.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.MilestoneRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.MilestoneResponseDto;
import com.femventure.ProjectManagement.domain.entity.Milestone;
import com.femventure.ProjectManagement.domain.entity.Project;
import com.femventure.ProjectManagement.domain.interfaces.repository.IMilestoneRepository;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectRepository;
import com.femventure.ProjectManagement.domain.interfaces.service.IMilestoneService;
import com.femventure.ProjectManagement.domain.interfaces.service.IProjectService;
import com.femventure.ProjectManagement.shared.MilestoneStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        int totalMilestones = project.getTotalMilestones();

        Milestone lastMilestone = milestoneRepository.findTopByProjectIdOrderByIdDesc(projectId);
        if (lastMilestone != null && (lastMilestone.getStatus() != MilestoneStatus.COMPLETED || lastMilestone.getStatus()!=MilestoneStatus.APPROVED) && currentMilestonesCount != 0) {
            throw new IllegalStateException("No se puede crear un nuevo hito porque el último hito no está completado o aprobado");
        }

        var milestone = new Milestone(milestoneRequestDto, projectId);
        milestone.setFundingGoal(project.getTotalFundingGoal()/totalMilestones);
        if(currentMilestonesCount == 0) {
            milestone.setStatus(MilestoneStatus.PENDING);
            milestone.setStartDate(LocalDate.now());
        }
        else {
            milestone.setStatus(MilestoneStatus.IN_PROGRESS);
            milestone.setStartDate(LocalDate.now());
        }

        milestoneRepository.save(milestone);

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

    @Override
    public MilestoneResponseDto updateMilestoneStatus(Long milestoneId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));

        if (milestone.getStatus().equals("CASH OUT") || milestone.getStatus().equals("APPROVED")) {
            throw new IllegalStateException("Milestone already cashed out or it is approved, so it cannot be updated.");
        }

        if (milestone.getStatus() == MilestoneStatus.PENDING) {
            milestone.setStatus(MilestoneStatus.APPROVED);
        }
        // Si el hito está en estado IN_PROGRESS y ha alcanzado el funding goal con un 5% extra
        else if (milestone.getStatus() == MilestoneStatus.IN_PROGRESS
                && milestone.getFundingGoal() <= (milestone.getFundsRaised() + milestone.getFundsRaised() * 0.05)) {
            milestone.setStatus(MilestoneStatus.COMPLETED);
            milestone.setBusinessCommision(milestone.getFundingGoal() * 0.05);
            milestone.setEndDate(LocalDate.now());
        }
        // Si el hito está en estado APPROVED, cambiar a CASH_OUT
        else if (milestone.getStatus() == MilestoneStatus.APPROVED) {
            milestone.setStatus(MilestoneStatus.CASH_OUT);
            milestone.setCashedOutDate(LocalDate.now());
        }

        milestoneRepository.save(milestone);
        return modelMapper.map(milestone, MilestoneResponseDto.class);
    }
}