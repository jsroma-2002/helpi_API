package com.telegaming.helpi.controller;

import com.telegaming.helpi.domain.model.Game;

import com.telegaming.helpi.domain.service.GameService;
import com.telegaming.helpi.resource.game.GameResource;
import com.telegaming.helpi.resource.game.SaveGameResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(path = "api/games")
public class GameController {
    
    @Autowired
    private GameService gameService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Games", description = "Get all Games", tags = {"games"})
    @GetMapping
    public Page<GameResource> getAllGames(Pageable pageable){
        Page<Game> gamePage = gameService.getAllGames(pageable);
        List<GameResource> resources = gamePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Game By Id", description = "Get Game By Id", tags = {"games"})
    @GetMapping("/{gameId}")
    public GameResource getGameById(@PathVariable Long gameId){
        return convertToResource(gameService.getGameById(gameId));
    }

    @Operation(summary = "Post Game", description = "Post Game", tags = {"games"})
    @PostMapping
    public GameResource createGame(@Valid @RequestBody SaveGameResource resource){
        Game game = convertToEntity(resource);
        return convertToResource(gameService.createGame(game));
    }

    @Operation(summary = "Update Game", description = "Update Game", tags = {"games"})
    @PutMapping("/{gameId}")
    public GameResource updateGame(@PathVariable long gameId, @Valid @RequestBody SaveGameResource resource){
        Game game = convertToEntity(resource);
        return convertToResource(gameService.updateGame(gameId, game));
    }

    @Operation(summary = "Delete Game", description = "Delete Game", tags = {"games"})
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Game> deleteGame(@PathVariable long gameId){
        return gameService.deleteGame(gameId);
    }

    private Game convertToEntity(SaveGameResource resource) {
        return mapper.map(resource, Game.class);
    }

    private GameResource convertToResource(Game entity){
        return mapper.map(entity, GameResource.class);
    }
}
