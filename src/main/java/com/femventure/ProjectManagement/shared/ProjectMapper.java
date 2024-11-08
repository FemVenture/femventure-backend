package com.femventure.ProjectManagement.shared;

import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.ProjectResponseDto;
import com.femventure.ProjectManagement.domain.entity.Project;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequestDto dto) {
        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setTotalFundingGoal(dto.getTotalFundingGoal());
        project.setTag(dto.getTag());
        project.setStatus("Active");
        project.setFundsRaised(BigDecimal.ZERO);
        return project;
    }

    public ProjectResponseDto toResponseDto(Project project) {
        ProjectResponseDto responseDto = new ProjectResponseDto();
        responseDto.setId(project.getId());
        responseDto.setTitle(project.getTitle());
        responseDto.setDescription(project.getDescription());
        responseDto.setStartDate(project.getStartDate());
        responseDto.setEndDate(project.getEndDate());
        responseDto.setTotalFundingGoal(project.getTotalFundingGoal());
        responseDto.setFundsRaised(project.getFundsRaised());
        responseDto.setStatus(project.getStatus());
        responseDto.setTag(project.getTag());
        return responseDto;
    }
}