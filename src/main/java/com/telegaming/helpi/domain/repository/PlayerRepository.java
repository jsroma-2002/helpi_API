package com.telegaming.helpi.domain.repository;

import com.telegaming.helpi.domain.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    public Page<Player> findById(Long id, Pageable page);
    Boolean existsByEmail(String email);
}
