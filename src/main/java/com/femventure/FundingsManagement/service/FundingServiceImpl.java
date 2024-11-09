package com.femventure.FundingsManagement.service;
import com.femventure.FundingsManagement.application.validation.FundingValidation;
import com.femventure.FundingsManagement.domain.dto.Funding.request.FundingRequestDto;
import com.femventure.FundingsManagement.domain.dto.Funding.response.FundingResponseDto;
import com.femventure.FundingsManagement.domain.entity.Funding;
import com.femventure.FundingsManagement.domain.interfaces.repository.IFundingRepository;
import com.femventure.FundingsManagement.domain.interfaces.service.IFundingService;
import com.femventure.ProjectManagement.domain.entity.Milestone;
import com.femventure.ProjectManagement.domain.interfaces.repository.IMilestoneRepository;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectPictureRepository;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectRepository;
import com.femventure.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FundingServiceImpl implements IFundingService {
    private final IFundingRepository fundingRepository;
    private final ModelMapper modelMapper;
    private final IProjectRepository projectRepository;
    private final IMilestoneRepository milestoneRepository;

    public FundingServiceImpl(IFundingRepository fundingRepository, ModelMapper modelMapper, IProjectRepository projectRepository, IMilestoneRepository milestoneRepository) {
        this.fundingRepository = fundingRepository;
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
        this.milestoneRepository = milestoneRepository;
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


        var milestoneOpt = milestoneRepository.findById(funding.getMilestoneId());
        if (milestoneOpt.isEmpty()) {
            throw new ResourceNotFoundException("Milestone not found for id: " + funding.getMilestoneId());
        }

        Milestone milestone = milestoneOpt.get();


        milestone.setFundsRaised(milestone.getFundsRaised() + funding.getAmount());
        milestoneRepository.save(milestone);


        updateProjectFundingStats(milestone.getProjectId());


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

    private void updateProjectFundingStats(Long projectId) {
        var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found for id: " + projectId));


        double totalFundsRaised = milestoneRepository.findByProjectId(projectId)
                .stream()
                .mapToDouble(Milestone::getFundsRaised)
                .sum();

        project.setFundsRaised(totalFundsRaised);
        projectRepository.save(project);
    }
}
