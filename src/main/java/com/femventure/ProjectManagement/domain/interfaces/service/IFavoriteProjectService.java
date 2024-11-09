package com.femventure.ProjectManagement.domain.interfaces.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.FavoriteProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.FavoriteProjectResponseDto;

import java.util.List;

public interface IFavoriteProjectService {

    FavoriteProjectResponseDto markProjectAsFavorite(Long projectId, Long entrepreneurId);

    void unmarkProjectAsFavorite(Long projectId, Long entrepreneurId);

    List<FavoriteProjectResponseDto> getFavoriteProjectsByEntrepreneur(Long entrepreneurId);
}
