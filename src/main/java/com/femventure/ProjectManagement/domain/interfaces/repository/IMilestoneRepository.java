package com.femventure.ProjectManagement.domain.interfaces.repository;

import com.femventure.ProjectManagement.domain.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findByProjectId(Long projectId);

    long countByProjectId(Long projectId);

    Milestone findTopByProjectIdOrderByIdDesc(Long projectId);
}
