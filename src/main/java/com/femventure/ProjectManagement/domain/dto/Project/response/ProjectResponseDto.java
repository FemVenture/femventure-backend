package com.femventure.ProjectManagement.domain.dto.Project.response;

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
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalFundingGoal;
    private BigDecimal fundsRaised;
    private String status;
    private String tag;
    private List<String> pictureUrls;
}
