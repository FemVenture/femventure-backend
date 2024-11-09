package com.femventure.ProjectManagement.domain.dto.Project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MilestoneRequestDto {
    private String title;
    private String description;
    private LocalDate completionDate;
    private String status;
    private String evidence;
    private LocalDate startDate;
    private LocalDate endDate;
    private double fundingGoal;
    private double fundsRaised;

}
