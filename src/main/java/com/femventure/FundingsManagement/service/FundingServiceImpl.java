package com.femventure.FundingsManagement.service;
import com.femventure.FundingsManagement.application.validation.FundingValidation;
import com.femventure.FundingsManagement.domain.dto.Funding.request.FundingRequestDto;
import com.femventure.FundingsManagement.domain.dto.Funding.response.FundingResponseDto;
import com.femventure.FundingsManagement.domain.entity.Funding;
import com.femventure.FundingsManagement.domain.interfaces.repository.IFundingRepository;
import com.femventure.FundingsManagement.domain.interfaces.service.IFundingService;
import com.femventure.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FundingServiceImpl implements IFundingService {
    private final IFundingRepository fundingRepository;
    private final ModelMapper modelMapper;

    public FundingServiceImpl(IFundingRepository fundingRepository, ModelMapper modelMapper) {
        this.fundingRepository = fundingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FundingResponseDto createFunding(FundingRequestDto fundingRequestDto) {
        FundingValidation.validateFunding(fundingRequestDto);

        Funding funding = new Funding();
        funding.setEntrepreneurId(fundingRequestDto.getEntrepreneurId());
        funding.setMilestoneId(fundingRequestDto.getMilestoneId());
        funding.setAmount(fundingRequestDto.getAmount());
        funding.setFundingDate(LocalDateTime.now());
        var createdFunding = fundingRepository.save(funding);
        return modelMapper.map(createdFunding, FundingResponseDto.class);
    }

    @Override
    public List<FundingResponseDto> getFundingsByMilestoneId(Long milestoneId) {
        List<Funding> fundings = fundingRepository.findAllByMilestoneId(milestoneId);
        if (fundings.isEmpty()) {
            throw new ResourceNotFoundException("No hay financiamientos para este hito");
        }
        return fundings.stream()
                .map(funding -> modelMapper.map(funding, FundingResponseDto.class))
                .toList();
    }
}
