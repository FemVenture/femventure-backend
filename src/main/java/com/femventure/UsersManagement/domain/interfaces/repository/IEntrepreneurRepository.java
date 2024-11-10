package com.femventure.UsersManagement.domain.interfaces.repository;

import com.femventure.UsersManagement.domain.entity.Entrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IEntrepreneurRepository extends JpaRepository<Entrepreneur, Long> {
    Optional<Entrepreneur> findById(Long id);
}
