package com.femventure.ProjectManagement.domain.entity;

import com.femventure.ProjectManagement.domain.dto.Project.request.ProjectRequestDto;
import com.femventure.UsersManagement.domain.entity.Entrepreneur;
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


    public Project(ProjectRequestDto projectRequestDto, Long entrepreneurId) {
        this.title = projectRequestDto.getTitle();
        this.description = projectRequestDto.getDescription();
        this.startDate = projectRequestDto.getStartDate();
        this.endDate = projectRequestDto.getEndDate();
        this.tag = projectRequestDto.getTag();
        this.entrepreneurId = entrepreneurId;
        this.text = projectRequestDto.getText();
        this.totalMilestones = projectRequestDto.getTotalMilestones();
    }


    public void update(ProjectRequestDto projectRequestDto) {
        this.title = projectRequestDto.getTitle();
        this.description = projectRequestDto.getDescription();
        this.startDate = projectRequestDto.getStartDate();
        this.endDate = projectRequestDto.getEndDate();
        this.tag = projectRequestDto.getTag();
        this.text = projectRequestDto.getText();
        this.totalMilestones = projectRequestDto.getTotalMilestones();
    }





}
