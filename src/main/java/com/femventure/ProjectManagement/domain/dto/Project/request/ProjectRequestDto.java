package com.femventure.ProjectManagement.domain.dto.Project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
    private String title;
    private String description;
    private String tag;
    private String text;
    private int totalMilestones;
    private double totalFundingGoal;
}
