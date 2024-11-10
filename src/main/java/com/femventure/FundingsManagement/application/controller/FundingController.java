package com.femventure.FundingsManagement.application.controller;
import com.femventure.FundingsManagement.domain.dto.Funding.request.FundingRequestDto;
import com.femventure.FundingsManagement.domain.dto.Funding.response.FundingResponseDto;
import com.femventure.FundingsManagement.domain.interfaces.service.IFundingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Funding Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class FundingController {
    private final IFundingService fundingService;

    public FundingController(IFundingService fundingService) {
        this.fundingService = fundingService;
    }

    @Operation(summary = "Create a funding")
    @PostMapping("/fundings")
    public ResponseEntity<FundingResponseDto> createFunding(@RequestBody FundingRequestDto fundingRequestDto) {
        var response = fundingService.createFunding(fundingRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get fundings by milestoneId")
    @GetMapping("/fundings/{milestoneId}")
    public ResponseEntity<List<FundingResponseDto>> getFundingsByMilestoneId(@PathVariable Long milestoneId) {
        var response = fundingService.getFundingsByMilestoneId(milestoneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}