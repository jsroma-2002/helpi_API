package com.telegaming.helpi.domain.service;

import com.telegaming.helpi.domain.model.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommunityService {
    Page<Community> getAllCommunities(Pageable pageable);
    Community getCommunityById(Long communityId);
    Page<Community> getCommunitiesByPlayerId(Long playerId, Pageable pageable);
    Community createCommunity(Community community);
    Community updateCommunity(Long communityId, Community communityRequest);
    ResponseEntity<?> deleteCommunity(Long communityId);
}
