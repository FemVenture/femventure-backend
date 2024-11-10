package com.femventure.FundingsManagement.domain.dto.Funding.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundingResponseDto {
    private Long id;
    private Float amount;
    private LocalDateTime fundingDate;
    private Long entrepreneurId;
    private Long milestoneId;
}
