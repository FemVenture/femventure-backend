package com.femventure.ProjectManagement.domain.interfaces.repository;

import com.femventure.ProjectManagement.domain.entity.FavoriteProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFavoriteProjectRepository extends JpaRepository<FavoriteProject, Long> {
    Optional<FavoriteProject> findByEntrepreneurIdAndProjectId(Long entrepreneurId, Long projectId);
    List<FavoriteProject> findByEntrepreneurId(Long entrepreneurId);

}
