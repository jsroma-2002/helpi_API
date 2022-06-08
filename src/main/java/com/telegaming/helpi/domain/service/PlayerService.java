package com.telegaming.helpi.domain.service;

import com.telegaming.helpi.domain.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PlayerService {
    Page<Player> getAllPlayers(Pageable pageable);
    Player getPlayerById(Long playerId);
    Page<Player> getPlayerByCommunityId(Long communityId, Pageable pageable);
    Player createPlayer(Player player);
    Player updatePlayer(Long playerId, Player playerRequest);
    Player purchaseTrainingMaterial(Long playerId, Long trainingId);
    Player joinCommunity(Long playerId, Long communityId);
    ResponseEntity<?> deletePlayer(Long playerId);
    Player login(String email, String password);
}
