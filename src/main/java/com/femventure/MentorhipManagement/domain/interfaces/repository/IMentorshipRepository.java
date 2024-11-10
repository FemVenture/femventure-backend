package com.femventure.MentorhipManagement.domain.interfaces.repository;
import com.femventure.MentorhipManagement.domain.entity.Mentorship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMentorshipRepository extends JpaRepository<Mentorship, Long> {
    Optional<Mentorship> findByMentorId(Long mentorId);
}
