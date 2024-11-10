package com.femventure.FundingsManagement.domain.dto.Funding.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundingRequestDto {
    private Float amount;
    private Long entrepreneurId;
    private Long milestoneId;
}
