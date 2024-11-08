package com.femventure.ProjectManagement.domain.interfaces.service;

import com.femventure.ProjectManagement.domain.dto.Project.request.FavoriteProjectRequestDto;
import com.femventure.ProjectManagement.domain.dto.Project.response.FavoriteProjectResponseDto;

import java.util.List;

public interface IFavoriteProjectService {
    FavoriteProjectResponseDto markAsFavorite(FavoriteProjectRequestDto requestDto); // Marcar un proyecto como favorito
    FavoriteProjectResponseDto unmarkAsFavorite(Long entrepreneurId, Long projectId); // Quitar un proyecto de favoritos
    List<FavoriteProjectResponseDto> getFavoritesByEntrepreneur(Long entrepreneurId); // Obtener todos los proyectos favoritos de un emprendedor

}
