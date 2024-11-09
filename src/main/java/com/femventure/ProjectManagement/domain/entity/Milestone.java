package com.femventure.ProjectManagement.domain.entity;

import com.femventure.ProjectManagement.domain.dto.Project.request.MilestoneRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "milestones")
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String evidence;
    private String status;

    private LocalDate startDate;
    private LocalDate completionDate;
    private LocalDate endDate;

    private double fundingGoal;
    private double fundsRaised;


    @Column(name = "project_id", nullable = false)
    private Long projectId;



    public Milestone(MilestoneRequestDto requestDto, Long projectId) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.status = requestDto.getStatus();
        this.evidence = requestDto.getEvidence();

        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.completionDate = requestDto.getCompletionDate() ;

        this.fundingGoal = requestDto.getFundingGoal();
        this.projectId = projectId;
        this.fundsRaised =requestDto.getFundsRaised();
    }

    public void update(MilestoneRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.status = requestDto.getStatus();
        this.evidence = requestDto.getEvidence();

        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.completionDate = requestDto.getCompletionDate() ;

        this.fundingGoal = requestDto.getFundingGoal();
        this.fundsRaised =requestDto.getFundsRaised();
    }


}
