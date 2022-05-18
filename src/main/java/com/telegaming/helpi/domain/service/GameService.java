package com.telegaming.helpi.domain.service;

import com.telegaming.helpi.domain.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GameService {
    Page<Game> getAllGames(Pageable pageable);
    Game getGameById(Long gameId);
    Game createGame(Game game);
    Game updateGame(Long gameId, Game gameRequest);
    ResponseEntity<?> deleteGame(Long gameId);
}
