package com.sea.challenge.register.repositories;

import com.sea.challenge.register.models.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query("SELECT e.email FROM Email e WHERE e.email IN (:emails)")
    List<String> existsAllByEmails(@Param("emails") List<String> emails);
}
