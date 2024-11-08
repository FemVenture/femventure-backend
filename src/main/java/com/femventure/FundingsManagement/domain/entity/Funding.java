package com.femventure.FundingsManagement.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fundings")
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float amount;
    private LocalDateTime fundingDate;
    private Long entrepreneurId;
    private Long milestoneId;
}