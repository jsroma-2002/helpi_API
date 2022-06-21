package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.Game;
import com.telegaming.helpi.domain.repository.GameRepository;
import com.telegaming.helpi.domain.service.GameService;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Page<Game> getAllGames(Pageable pageable) {

        return gameRepository.findAll(pageable);
    }

    @Override
    public Game getGameById(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(()->new ResourceNotFoundException("Game", "Id",gameId));
    }

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(Long gameId, Game gameRequest) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(()->new ResourceNotFoundException("Game", "Id", gameId));

        game.setGameName(gameRequest.getGameName());
        game.setDescription(gameRequest.getDescription());
        game.setCoverUri(gameRequest.getCoverUri());

        return gameRepository.save(game);
    }

    @Override
    public ResponseEntity<Game> deleteGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(()->new ResourceNotFoundException("Game", "Id", gameId));

        gameRepository.delete(game);

        return ResponseEntity.ok().build();
    }
}
