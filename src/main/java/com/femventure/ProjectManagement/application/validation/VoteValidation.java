package com.femventure.ProjectManagement.application.validation;

import com.femventure.ProjectManagement.domain.dto.Vote.request.VoteRequestDto;
import com.femventure.Shared.exception.ValidationException;

public class VoteValidation {
    public static void validateVote(VoteRequestDto voteRequestDto) {
        if (voteRequestDto.getMentorId() == null) {
            throw new ValidationException("El mentorId es obligatorio");
        }
        if (voteRequestDto.getMilestoneId() == null) {
            throw new ValidationException("El milestoneId es obligatorio");
        }
    }
}