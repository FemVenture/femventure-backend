package com.femventure.ProjectManagement.domain.entity;

import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    private double totalFundingGoal = 0.0;
    private double fundsRaised = 0.0;

    private String tag;
    private String text;

    @Column(name = "entrepreneur_id", nullable = false)
    private Long entrepreneurId;

    private int totalMilestones;

    @Column(name = "milestone_id", nullable = false)
    private Long currentMilestoneId;


    public Project(ProjectRequestDto projectRequestDto, Long entrepreneurId) {
        this.title = projectRequestDto.getTitle();
        this.description = projectRequestDto.getDescription();
        this.tag = projectRequestDto.getTag();
        this.entrepreneurId = entrepreneurId;
        this.text = projectRequestDto.getText();
        this.totalMilestones = projectRequestDto.getTotalMilestones();
        this.currentMilestoneId = 0L;
        this.totalFundingGoal = projectRequestDto.getTotalFundingGoal();
    }

    public void update(ProjectUpdateRequestDto projectRequestDto) {
        this.title = projectRequestDto.getTitle();
        this.description = projectRequestDto.getDescription();
        this.tag = projectRequestDto.getTag();
        this.text = projectRequestDto.getText();
    }


}