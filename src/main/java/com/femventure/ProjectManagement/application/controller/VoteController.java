package com.femventure.ProjectManagement.application.controller;
import com.femventure.ProjectManagement.domain.dto.Vote.request.VoteRequestDto;
import com.femventure.ProjectManagement.domain.dto.Vote.response.VoteResponseDto;
import com.femventure.ProjectManagement.domain.interfaces.service.IVoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Vote Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class VoteController {
    private final IVoteService voteService;

    public VoteController(IVoteService voteService) {
        this.voteService = voteService;
    }

    @Operation(summary = "Create a vote")
    @PostMapping("/votes")
    public ResponseEntity<VoteResponseDto> createVote(@RequestBody VoteRequestDto voteRequestDto) {
        var response = voteService.createVote(voteRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get vote by mentorId and milestoneId")
    @GetMapping("/votes/{mentorId}/{milestoneId}")
    public ResponseEntity<VoteResponseDto> getVoteByMentorIdAndMilestoneId(@PathVariable Long mentorId, @PathVariable Long milestoneId) {
        var response = voteService.getVoteByMentorIdAndMilestoneId(mentorId, milestoneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Approve a milestone")
    @GetMapping("/votes/{milestoneId}")
    public ResponseEntity<Boolean> approveMilestone(@PathVariable Long milestoneId) {
        var response = voteService.approveMilestone(milestoneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}