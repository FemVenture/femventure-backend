package com.femventure.FundingsManagement.domain.interfaces.repository;

import com.femventure.FundingsManagement.domain.entity.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFundingRepository extends JpaRepository<Funding, Long>{
    List<Funding> findAllByMilestoneId(Long milestoneId);
    //Optional<Funding> findByMentorIdAndMilestoneId(Long mentorId, Long milestoneId);
}
