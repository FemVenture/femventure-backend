package com.femventure.ProjectManagement.domain.entity;

import com.femventure.UsersManagement.domain.entity.Entrepreneur;
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

    @Column(name = "entrepreneur_id", nullable = false)
    private Long entrepreneurId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;


    private boolean isFavorite;

    public FavoriteProject(Long entrepreneurId, Long projectId, boolean isFavorite) {
        this.entrepreneurId = entrepreneurId;
        this.projectId = projectId;
        this.isFavorite = isFavorite;
    }

}
