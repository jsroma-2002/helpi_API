package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.Player;
import com.telegaming.helpi.domain.model.TrainingMaterial;
import com.telegaming.helpi.domain.repository.PlayerRepository;
import com.telegaming.helpi.domain.repository.TrainingMaterialRepository;
import com.telegaming.helpi.domain.service.PlayerService;
import com.telegaming.helpi.exception.ResourceIncorrectData;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TrainingMaterialRepository trainingMaterialRepository;

    @Override
    public Page<Player> getAllPlayers(Pageable pageable) {

        return playerRepository.findAll(pageable);
    }

    @Override
    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException("Player", "Id",playerId));
    }

    @Override
    public Player createPlayer(Player player) {
        if (playerRepository.existsByEmail(player.getEmail()))
        {
            throw new ResourceIncorrectData("El email ya esta en uso");
        }
        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(Long playerId, Player playerRequest) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException("Player", "Id", playerId));

        player.setBirthDate(playerRequest.getBirthDate());
        player.setName(playerRequest.getName());
        player.setEmail(playerRequest.getEmail());
        player.setPassword(playerRequest.getPassword());

        return playerRepository.save(player);

    }

    @Override
    public Player purchaseTrainingMaterial(Long playerId, Long trainingId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException("Player", "Id", playerId));

        TrainingMaterial trainingMaterial = trainingMaterialRepository.findById(trainingId)
                .orElseThrow(()->new ResourceNotFoundException("Training", "Id", trainingId));

        if (!player.getOwnedTrainingMaterials().contains(trainingMaterial)){
            player.purchaseTraining(trainingMaterial);
            trainingMaterial.getOwnerPlayers().add(player);
        }
        trainingMaterialRepository.save(trainingMaterial);

        return playerRepository.save(player);
    }

    @Override
    public ResponseEntity<?> deletePlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException("Player", "Id", playerId));

        playerRepository.delete(player);

        return ResponseEntity.ok().build();
    }

    @Override
    public Player login(String email, String password) {

        return playerRepository.findByEmailAndPassword(email, password);
    }
}
