package com.femventure.ProjectManagement.service;

import com.femventure.ProjectManagement.domain.dto.Project.response.FavoriteProjectResponseDto;
import com.femventure.ProjectManagement.domain.entity.FavoriteProject;
import com.femventure.ProjectManagement.domain.entity.Project;
import com.femventure.ProjectManagement.domain.interfaces.repository.IFavoriteProjectRepository;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectRepository;
import com.femventure.ProjectManagement.domain.interfaces.service.IFavoriteProjectService;
import com.femventure.UsersManagement.domain.entity.Entrepreneur;
import com.femventure.UsersManagement.domain.interfaces.repository.IEntrepreneurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteProjectServiceImpl implements IFavoriteProjectService {

    private final IFavoriteProjectRepository favoriteProjectRepository;

    private final IEntrepreneurRepository entrepreneurRepository;

    private final IProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public FavoriteProjectServiceImpl(IFavoriteProjectRepository favoriteProjectRepository, IEntrepreneurRepository entrepreneurRepository, IProjectRepository projectRepository, ModelMapper modelMapper) {
        this.favoriteProjectRepository = favoriteProjectRepository;
        this.entrepreneurRepository = entrepreneurRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public FavoriteProjectResponseDto markProjectAsFavorite(Long projectId, Long entrepreneurId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new RuntimeException("Entrepreneur not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        FavoriteProject favoriteProject = favoriteProjectRepository
                .findByEntrepreneurIdAndProjectId(entrepreneur.getId(), project.getId())
                .orElse(new FavoriteProject( entrepreneurId, projectId, true ));
        FavoriteProject savedFavoriteProject = favoriteProjectRepository.save(favoriteProject);
        return modelMapper.map(savedFavoriteProject,FavoriteProjectResponseDto.class);
    }

    @Override
    public void unmarkProjectAsFavorite(Long projectId, Long entrepreneurId) {
        FavoriteProject favoriteProject = favoriteProjectRepository
                .findByEntrepreneurIdAndProjectId(entrepreneurId, projectId)
                .orElseThrow(() -> new RuntimeException("Favorite project not found"));

        favoriteProjectRepository.delete(favoriteProject);

    }

    @Override
    public List<FavoriteProjectResponseDto> getFavoriteProjectsByEntrepreneur(Long entrepreneurId) {
        List<FavoriteProject> favoriteProjects = favoriteProjectRepository.findByEntrepreneurId(entrepreneurId);

        return favoriteProjects.stream()
                .map(project -> modelMapper.map(project, FavoriteProjectResponseDto.class))
                .collect(Collectors.toList());
    }
}
