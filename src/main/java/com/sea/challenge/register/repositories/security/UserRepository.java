package com.sea.challenge.register.repositories.security;

import com.sea.challenge.register.models.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
