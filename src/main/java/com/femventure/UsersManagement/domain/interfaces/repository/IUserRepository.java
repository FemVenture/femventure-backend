package com.femventure.UsersManagement.domain.interfaces.repository;

import com.femventure.UsersManagement.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
}
