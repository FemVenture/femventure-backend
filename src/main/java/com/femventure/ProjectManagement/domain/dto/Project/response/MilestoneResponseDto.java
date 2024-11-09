package com.femventure.ProjectManagement.domain.dto.Project.response;

import com.femventure.ProjectManagement.domain.entity.Milestone;
import com.femventure.ProjectManagement.shared.MilestoneStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MilestoneResponseDto {
    private Long id;
    private String title;
    private String description;


    private LocalDate startDate;
    private LocalDate endDate;
    private double fundingGoal;
    private double fundsRaised;
    private MilestoneStatus status;
}
