package com.femventure.ProjectManagement.domain.dto.Project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProjectRequestDto {
    private Long projectId;
    private boolean isFavorite;
}
