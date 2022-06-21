package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.Community;
import com.telegaming.helpi.domain.model.Player;
import com.telegaming.helpi.domain.model.TrainingMaterial;
import com.telegaming.helpi.domain.repository.CommunityRepository;
import com.telegaming.helpi.domain.repository.PlayerRepository;
import com.telegaming.helpi.domain.repository.TrainingMaterialRepository;
import com.telegaming.helpi.domain.service.PlayerService;
import com.telegaming.helpi.exception.ResourceIncorrectData;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TrainingMaterialRepository trainingMaterialRepository;

    @Autowired
    private CommunityRepository communityRepository;

    private static final String PLAYER = "Player";

    @Override
    public Page<Player> getAllPlayers(Pageable pageable) {

        return playerRepository.findAll(pageable);
    }

    @Override
    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException(PLAYER, "Id",playerId));
    }

    @Override
    public Page<Player> getPlayerByCommunityId(Long communityId, Pageable pageable) {
        return communityRepository.findById(communityId).map(community -> {
            Set<Player> players = community.getMemberPlayers();
            List<Player> playersList = new ArrayList<>(players);
            return new PageImpl<>(playersList, pageable, players.size());
        }).orElseThrow(()->new ResourceNotFoundException("Community","Id",communityId));
    }

    @Override
    public Player createPlayer(Player player) {

        Boolean exist = playerRepository.existsByEmail(player.getEmail());

        if (Boolean.TRUE.equals(exist)) {
            throw new ResourceIncorrectData("El email ya esta en uso");
        } else {
            return playerRepository.save(player);
        }
    }

    @Override
    public Player updatePlayer(Long playerId, Player playerRequest) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException(PLAYER, "Id", playerId));

        player.setBirthDate(playerRequest.getBirthDate());
        player.setName(playerRequest.getName());
        player.setEmail(playerRequest.getEmail());
        player.setPassword(playerRequest.getPassword());
        player.setBalance(playerRequest.getBalance());

        return playerRepository.save(player);

    }

    @Override
    public Player purchaseTrainingMaterial(Long playerId, Long trainingId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException(PLAYER, "Id", playerId));

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
    public Player joinCommunity(Long playerId, Long communityId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException(PLAYER, "Id", playerId));

        Community community = communityRepository.findById(communityId)
                .orElseThrow(()->new ResourceNotFoundException("Community", "Id", communityId));

        if (!player.getBelongCommunities().contains(community)){
            player.joinCommunity(community);
            community.getMemberPlayers().add(player);
        }
        communityRepository.save(community);

        return playerRepository.save(player);
    }

    @Override
    public ResponseEntity<Player> deletePlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException(PLAYER, "Id", playerId));

        playerRepository.delete(player);

        return ResponseEntity.ok().build();
    }

    @Override
    public Player login(String email, String password) {

        return playerRepository.findByEmailAndPassword(email, password);
    }
}
