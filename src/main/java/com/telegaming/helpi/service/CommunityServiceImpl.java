package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.Community;
import com.telegaming.helpi.domain.repository.CommunityRepository;
import com.telegaming.helpi.domain.service.CommunityService;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;
    
    @Override
    public Page<Community> getAllCommunities(Pageable pageable) {
        return communityRepository.findAll(pageable);
    }

    @Override
    public Community getCommunityById(Long communityId) {
        return communityRepository.findById(communityId)
                .orElseThrow(()->new ResourceNotFoundException("Community", "Id",communityId));
    }

    @Override
    public Community createCommunity(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public Community updateCommunity(Long communityId, Community communityRequest) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(()->new ResourceNotFoundException("Community", "Id", communityId));

        community.setCommunityTitle(communityRequest.getCommunityTitle());
        community.setCommunityDescription(communityRequest.getCommunityDescription());

        return communityRepository.save(community);
    }

    @Override
    public ResponseEntity<?> deleteCommunity(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(()->new ResourceNotFoundException("Community", "Id", communityId));

        communityRepository.delete(community);

        return ResponseEntity.ok().build();
    }
}
