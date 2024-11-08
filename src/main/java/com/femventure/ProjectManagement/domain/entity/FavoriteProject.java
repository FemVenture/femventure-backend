package com.femventure.ProjectManagement.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorite_projects")
public class FavoriteProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    private boolean isFavorite;

}
