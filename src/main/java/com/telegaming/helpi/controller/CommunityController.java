package com.telegaming.helpi.controller;

import com.telegaming.helpi.domain.model.Community;
import com.telegaming.helpi.domain.service.CommunityService;
import com.telegaming.helpi.resource.community.CommunityResource;
import com.telegaming.helpi.resource.community.SaveCommunityResource;
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
@RequestMapping(path = "api/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Communities", description = "Get all Communities", tags = {"communities"})
    @GetMapping
    public Page<CommunityResource> getAllCommunities(Pageable pageable){
        Page<Community> communityPage = communityService.getAllCommunities(pageable);
        List<CommunityResource> resources = communityPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Community By Id", description = "Get Community By Id", tags = {"communities"})
    @GetMapping("/{communityId}")
    public CommunityResource getCommunityById(@PathVariable Long communityId){
        return convertToResource(communityService.getCommunityById(communityId));
    }

    @Operation(summary = "Get Communities By Player Id", description = "Get Communities By Player Id", tags = {"communities"})
    @GetMapping("/player/{playerId}")
    public Page<CommunityResource> getCommunityByPlayerId(Pageable pageable, @PathVariable Long playerId){
        Page<Community> communityPage = communityService.getCommunitiesByPlayerId(playerId, pageable);
        List<CommunityResource> resources = communityPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Post Community", description = "Post Community", tags = {"communities"})
    @PostMapping
    public CommunityResource createCommunity(@Valid @RequestBody SaveCommunityResource resource){
        Community community = convertToEntity(resource);
        return convertToResource(communityService.createCommunity(community));
    }

    @Operation(summary = "Update Community", description = "Update Community", tags = {"communities"})
    @PutMapping("/{communityId}")
    public CommunityResource updateCommunity(@PathVariable long communityId, @Valid @RequestBody SaveCommunityResource resource){
        Community community = convertToEntity(resource);
        return convertToResource(communityService.updateCommunity(communityId, community));
    }

    @Operation(summary = "Delete Community", description = "Delete Community", tags = {"communities"})
    @DeleteMapping("/{communityId}")
    public ResponseEntity<?> deleteCommunity(@PathVariable long communityId){
        return communityService.deleteCommunity(communityId);
    }

    private Community convertToEntity(SaveCommunityResource resource) {
        return mapper.map(resource, Community.class);
    }

    private CommunityResource convertToResource(Community entity){
        return mapper.map(entity, CommunityResource.class);
    }
}
