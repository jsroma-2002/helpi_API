package com.telegaming.helpi.domain.repository;

import com.telegaming.helpi.domain.model.Coach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {

    public Page<Coach> findById(Long Id, Pageable page);

    Boolean existsByEmail(String email);
}
