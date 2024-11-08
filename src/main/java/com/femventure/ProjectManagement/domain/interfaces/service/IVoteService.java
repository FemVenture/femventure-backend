package com.femventure.ProjectManagement.domain.interfaces.service;

import com.femventure.ProjectManagement.domain.dto.Vote.request.VoteRequestDto;
import com.femventure.ProjectManagement.domain.dto.Vote.response.VoteResponseDto;

public interface IVoteService {
    //POST
    public abstract VoteResponseDto createVote(VoteRequestDto voteRequestDto);
    //GET
    public abstract VoteResponseDto getVoteByMentorIdAndMilestoneId(Long mentorId, Long milestoneId);
    public abstract Boolean approveMilestone(Long milestoneId);
}