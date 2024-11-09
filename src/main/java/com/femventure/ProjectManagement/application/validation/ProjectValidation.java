package com.femventure.ProjectManagement.application.validation;
import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Vote.request.VoteRequestDto;
import com.femventure.Shared.exception.ValidationException;

public class ProjectValidation {
    public static void validateProject(ProjectRequestDto projectRequestDto) {
        if (projectRequestDto.getTotalFundingGoal() < 3000 ) {
            throw new ValidationException("El total de financiamiento debe ser mayor o igual a 3000");
        }
        if (projectRequestDto.getTotalMilestones() < 3 ) {
            throw new ValidationException("El total de hitos debe ser mayor o igual a 3");
        }
    }


}
