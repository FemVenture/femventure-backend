package com.femventure.ProjectManagement.domain.interfaces.repository;

import com.femventure.ProjectManagement.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {
    Project findByStatus(String status);
    Project findByTitle(String title);

    List<Project> findByCreatorId(Long creatorId);
}
