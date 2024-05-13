package com.sea.challenge.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sea.challenge.register.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
