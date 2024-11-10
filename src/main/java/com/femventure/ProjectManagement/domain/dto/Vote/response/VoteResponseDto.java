package com.femventure.ProjectManagement.domain.dto.Vote.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDto {
    private boolean voteValue;
    private Long milestoneId;
    private Long mentorId;
}