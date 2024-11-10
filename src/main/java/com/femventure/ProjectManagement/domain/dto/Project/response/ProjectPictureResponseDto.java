package com.femventure.ProjectManagement.domain.dto.Project.response;

import com.femventure.ProjectManagement.domain.entity.ProjectPicture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPictureResponseDto {
    private Long id;
    private String imageUrl;
    private Long projectId;

}
