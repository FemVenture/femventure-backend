package com.femventure.ProjectManagement.domain.interfaces.repository;

import com.femventure.ProjectManagement.domain.entity.Project;
import com.femventure.ProjectManagement.domain.entity.ProjectPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProjectPictureRepository extends JpaRepository<ProjectPicture, Long> {
    List<ProjectPicture> findByProjectId(Long projectId);
}
