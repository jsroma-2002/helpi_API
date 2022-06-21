package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.Community;
import com.telegaming.helpi.domain.repository.CommunityRepository;
import com.telegaming.helpi.domain.repository.PlayerRepository;
import com.telegaming.helpi.domain.service.CommunityService;
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
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private PlayerRepository playerRepository;

    private static final String COMMUNITY = "Community";
    
    @Override
    public Page<Community> getAllCommunities(Pageable pageable) {
        return communityRepository.findAll(pageable);
    }

    @Override
    public Community getCommunityById(Long communityId) {
        return communityRepository.findById(communityId)
                .orElseThrow(()->new ResourceNotFoundException(COMMUNITY, "Id",communityId));
    }

    @Override
    public Page<Community> getCommunitiesByPlayerId(Long playerId, Pageable pageable) {
        return playerRepository.findById(playerId).map(player -> {
            Set<Community> communities = player.getBelongCommunities();
            List<Community> communitiesList = new ArrayList<>(communities);
            return new PageImpl<>(communitiesList, pageable, communities.size());
        }).orElseThrow(()->new ResourceNotFoundException("Player","Id",playerId));
    }

    @Override
    public Community createCommunity(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public Community updateCommunity(Long communityId, Community communityRequest) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(()->new ResourceNotFoundException(COMMUNITY, "Id", communityId));

        community.setCommunityTitle(communityRequest.getCommunityTitle());
        community.setCommunityDescription(communityRequest.getCommunityDescription());

        return communityRepository.save(community);
    }

    @Override
    public ResponseEntity<Community> deleteCommunity(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(()->new ResourceNotFoundException(COMMUNITY, "Id", communityId));

        communityRepository.delete(community);

        return ResponseEntity.ok().build();
    }
}
