package com.femventure.ProjectManagement.domain.dto.Project.response;

import com.femventure.ProjectManagement.domain.entity.FavoriteProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProjectResponseDto {
    private Long id;
    private Long entrepreneurId;
    private Long projectId;
    private boolean isFavorite;

}
