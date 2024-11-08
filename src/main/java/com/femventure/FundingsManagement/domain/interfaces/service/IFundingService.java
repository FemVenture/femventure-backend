package com.femventure.FundingsManagement.domain.interfaces.service;

import com.femventure.FundingsManagement.domain.dto.Funding.request.FundingRequestDto;
import com.femventure.FundingsManagement.domain.dto.Funding.response.FundingResponseDto;

import java.util.List;

public interface IFundingService {
    //POST
    public abstract FundingResponseDto createFunding(FundingRequestDto fundingRequestDto);
    //GET
    public abstract List<FundingResponseDto> getFundingsByMilestoneId(Long milestoneId);
}