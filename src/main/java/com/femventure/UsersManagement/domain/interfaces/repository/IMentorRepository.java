package com.femventure.UsersManagement.domain.interfaces.repository;
import com.femventure.UsersManagement.domain.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IMentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findById(Long id);
}
