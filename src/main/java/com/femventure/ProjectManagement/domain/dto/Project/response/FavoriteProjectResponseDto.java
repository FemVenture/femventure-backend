package com.femventure.ProjectManagement.domain.dto.Project.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProjectResponseDto {
    private Long projectId;
    private Long entrepreneurId;
    private boolean isFavorite;
}
