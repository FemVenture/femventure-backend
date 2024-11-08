package com.femventure.ProjectManagement.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.MilestoneRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.MilestoneResponseDto;
import com.femventure.ProjectManagement.domain.interfaces.service.IMilestoneService;
import org.springframework.stereotype.Service;

@Service
public class MilestoneServicesImpl implements IMilestoneService {

    @Override
    public MilestoneResponseDto createMilestone(Long projectId, MilestoneRequestDto requestDto) {
        return null;
    }

    @Override
    public MilestoneResponseDto updateMilestone(Long milestoneId, MilestoneRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteMilestone(Long milestoneId) {

    }
}
