package com.telegaming.helpi.controller;

import com.telegaming.helpi.domain.model.Player;
import com.telegaming.helpi.domain.model.TrainingMaterial;
import com.telegaming.helpi.domain.service.PlayerService;
import com.telegaming.helpi.resource.player.PlayerResource;
import com.telegaming.helpi.resource.player.SaveLoginResource;
import com.telegaming.helpi.resource.player.SavePlayerResource;
import com.telegaming.helpi.resource.trainingMaterial.TrainingMaterialResource;
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
@RequestMapping(path = "api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Players", description = "Get all Players", tags = {"players"})
    @GetMapping
    public Page<PlayerResource> getAllPlayers(Pageable pageable){
        Page<Player> playerPage = playerService.getAllPlayers(pageable);
        List<PlayerResource> resources = playerPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Player By Id", description = "Get Player By Id", tags = {"players"})
    @GetMapping("/{playerId}")
    public PlayerResource getPlayerById(@PathVariable Long playerId){
        return convertToResource(playerService.getPlayerById(playerId));
    }

    @Operation(summary = "Get Player By Community Id", description = "Get Player By Community Id", tags = {"players"})
    @GetMapping("/community/{communityId}/players")
    public Page<PlayerResource> getPlayersByCommunityId(Pageable pageable, @PathVariable Long communityId){
        Page<Player> playerPage = playerService.getPlayerByCommunityId(communityId, pageable);
        List<PlayerResource> resources = playerPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Post Player", description = "Post Player", tags = {"players"})
    @PostMapping
    public PlayerResource createPlayer(@Valid @RequestBody SavePlayerResource resource){
        Player player = convertToEntity(resource);
        return convertToResource(playerService.createPlayer(player));
    }

    @Operation(summary = "Update Player", description = "Update Player", tags = {"players"})
    @PutMapping("/{playerId}")
    public PlayerResource updatePlayer(@PathVariable long playerId, @Valid @RequestBody SavePlayerResource resource){
        Player player = convertToEntity(resource);
        return convertToResource(playerService.updatePlayer(playerId, player));
    }

    @Operation(summary = "Purchase training material", description = "Purchase training material", tags = {"players"})
    @PutMapping("/{playerId}/training/{trainingId}")
    public PlayerResource purchaseTrainingMaterial(@PathVariable long playerId, @PathVariable long trainingId){

        return convertToResource(playerService.purchaseTrainingMaterial(playerId, trainingId));
    }

    @Operation(summary = "Join Community", description = "Join Community", tags = {"players"})
    @PutMapping("/{playerId}/community/{communityId}")
    public PlayerResource joinCommunity(@PathVariable long playerId, @PathVariable long communityId){

        return convertToResource(playerService.joinCommunity(playerId, communityId));
    }

    @Operation(summary = "Delete Player", description = "Delete Player", tags = {"players"})
    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable long playerId){
        return playerService.deletePlayer(playerId);
    }

    @Operation(summary = "Login", description = "Login By Email and Password", tags = {"players"})
    @PostMapping("/login")
    public PlayerResource login(@Valid @RequestBody SaveLoginResource resource){
        return convertToResource(playerService.login(resource.getEmail(), resource.getPassword()));
    }

    private Player convertToEntity(SavePlayerResource resource) {
        return mapper.map(resource, Player.class);
    }

    private PlayerResource convertToResource(Player entity){
        return mapper.map(entity, PlayerResource.class);
    }

}
