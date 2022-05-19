package com.telegaming.helpi.domain.repository;

import com.telegaming.helpi.domain.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    public Page<Game> findByGameId(Long id, Pageable page);
}
