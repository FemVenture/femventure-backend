package com.femventure.ProjectManagement.domain.dto.Project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String tag;
    private String text;
    private int totalMilestones;


}
