package com.femventure.ProjectManagement.domain.dto.Vote.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequestDto {
    private boolean voteValue;
    private Long milestoneId;
    private Long mentorId;
}