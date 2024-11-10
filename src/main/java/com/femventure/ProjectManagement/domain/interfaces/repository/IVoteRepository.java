package com.femventure.ProjectManagement.domain.interfaces.repository;

import com.femventure.ProjectManagement.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IVoteRepository extends JpaRepository<Vote, Long>{
    Optional<Vote> findByMentorIdAndMilestoneId(Long mentorId, Long milestoneId);
    List<Vote> findAllByMilestoneId(Long milestoneId);

}
