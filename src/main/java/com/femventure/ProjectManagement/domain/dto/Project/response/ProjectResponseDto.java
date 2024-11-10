package com.femventure.ProjectManagement.domain.dto.Project.response;

import com.femventure.ProjectManagement.domain.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {
    private Long id;
    private String title;
    private String description;
    private String text;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalFundingGoal;
    private double fundsRaised;
    private String tag;
    private Long entrepreneurId;
    private int totalMilestones;
    private Long currentMilestoneId;
}
