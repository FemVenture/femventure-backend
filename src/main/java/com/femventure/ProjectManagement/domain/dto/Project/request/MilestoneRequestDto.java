package com.femventure.ProjectManagement.domain.dto.Project.request;

import com.femventure.ProjectManagement.shared.MilestoneStatus;
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
    private String evidence;
}
