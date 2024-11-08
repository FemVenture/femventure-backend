package com.femventure.FundingsManagement.application.validation;

import com.femventure.FundingsManagement.domain.dto.Funding.request.FundingRequestDto;
import com.femventure.Shared.exception.ValidationException;

public class FundingValidation {
    public static void validateFunding (FundingRequestDto fundingRequestDto) {
        if (fundingRequestDto.getMilestoneId() == null) {
            throw new ValidationException("El milestoneId es obligatorio");
        }
        if (fundingRequestDto.getEntrepreneurId() == null) {
            throw new ValidationException("El entrepreneurId es obligatorio");
        }
        if(fundingRequestDto.getAmount() == null || fundingRequestDto.getAmount() <= 0) {
            throw new ValidationException("El amount es obligatorio");
        }
    }
}
