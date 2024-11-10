package com.femventure.ProjectManagement.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project_pictures")
public class ProjectPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    public ProjectPicture(String imageUrl, Long projectId) {
        this.imageUrl = imageUrl;
        this.projectId = projectId;
    }



}
