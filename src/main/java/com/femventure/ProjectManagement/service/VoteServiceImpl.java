package com.femventure.ProjectManagement.service;

import com.femventure.ProjectManagement.application.validation.VoteValidation;
import com.femventure.ProjectManagement.domain.dto.Vote.request.VoteRequestDto;
import com.femventure.ProjectManagement.domain.dto.Vote.response.VoteResponseDto;
import com.femventure.ProjectManagement.domain.entity.Vote;
import com.femventure.ProjectManagement.domain.interfaces.repository.IVoteRepository;
import com.femventure.ProjectManagement.domain.interfaces.service.IVoteService;
import com.femventure.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements IVoteService {
    private final IVoteRepository voteRepository;
    private final ModelMapper modelMapper;

    public VoteServiceImpl(IVoteRepository voteRepository, ModelMapper modelMapper) {
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VoteResponseDto createVote(VoteRequestDto voteRequestDto) {
        VoteValidation.validateVote(voteRequestDto);

        boolean mentorHasVoted = voteRepository.findByMentorIdAndMilestoneId(
                voteRequestDto.getMentorId(),
                voteRequestDto.getMilestoneId()
        ).isPresent();

        if (mentorHasVoted) {
            throw new ResourceNotFoundException("El mentor ya ha votado para este hito");
        }

        Vote vote = new Vote();
        vote.setMentorId(voteRequestDto.getMentorId());
        vote.setMilestoneId(voteRequestDto.getMilestoneId());
        vote.setVoteValue(voteRequestDto.isVoteValue());
        var createdVote = voteRepository.save(vote);
        return modelMapper.map(createdVote, VoteResponseDto.class);
    }

    @Override
    public VoteResponseDto getVoteByMentorIdAndMilestoneId(Long mentorId, Long milestoneId) {
        var vote = voteRepository.findByMentorIdAndMilestoneId(mentorId, milestoneId).orElseThrow(() ->
                new ResourceNotFoundException("No existe un voto con ese mentorId y milestoneId")
        );
        return modelMapper.map(vote, VoteResponseDto.class);
    }

    @Override
    public Boolean approveMilestone(Long milestoneId) {
        List<Vote> votes = voteRepository.findAllByMilestoneId(milestoneId);

        if (votes.isEmpty()) {
            return false;
        }

        long approvedVotesCount = votes.stream()
                .filter(vote -> Boolean.TRUE.equals(vote.isVoteValue()))
                .count();

        long totalVotes = votes.size();
        long majorityThreshold = totalVotes / 2;

        return approvedVotesCount > majorityThreshold;
    }
}