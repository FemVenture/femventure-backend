package com.femventure.ProjectManagement.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.FavoriteProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.FavoriteProjectResponseDto;
import com.femventure.ProjectManagement.domain.interfaces.service.IFavoriteProjectService;

import java.util.List;

public class FavoriteProjectImpl implements IFavoriteProjectService {
    @Override
    public FavoriteProjectResponseDto markAsFavorite(FavoriteProjectRequestDto requestDto) {
        return null;
    }

    @Override
    public FavoriteProjectResponseDto unmarkAsFavorite(Long entrepreneurId, Long projectId) {
        return null;
    }

    @Override
    public List<FavoriteProjectResponseDto> getFavoritesByEntrepreneur(Long entrepreneurId) {
        return List.of();
    }
}
