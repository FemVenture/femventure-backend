package com.femventure.ProjectManagement.domain.entity;

import com.femventure.ProjectManagement.domain.dto.Project.request.MilestoneRequestDto;
import com.femventure.ProjectManagement.shared.MilestoneStatus;
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

    @Enumerated(EnumType.STRING)
    private MilestoneStatus status;

    private LocalDate startDate;
    private LocalDate cashedOutDate;
    private LocalDate endDate;

    private double fundingGoal;
    private double fundsRaised;
    private double businessCommision;

    @Column(name = "project_id", nullable = false)
    private Long projectId;


    public Milestone(MilestoneRequestDto requestDto, Long projectId) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.evidence = requestDto.getEvidence();
        this.projectId = projectId;
        this.status = MilestoneStatus.PENDING;
        this.fundsRaised = 0.0;
        this.businessCommision = 0.0;
        this.fundsRaised = 0.0;
        this.businessCommision = 0.0;
    }

    public void update(MilestoneRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.evidence = requestDto.getEvidence();
    }

    public void setFundsRaised(double amount) {
        this.fundsRaised += amount;
    }
}