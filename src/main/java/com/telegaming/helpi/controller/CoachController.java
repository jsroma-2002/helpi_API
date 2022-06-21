package com.telegaming.helpi.controller;

import com.telegaming.helpi.domain.model.Coach;
import com.telegaming.helpi.domain.service.CoachService;
import com.telegaming.helpi.resource.coach.CoachResource;
import com.telegaming.helpi.resource.coach.SaveCoachResource;
import com.telegaming.helpi.resource.coach.SaveLoginResource;
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
@RequestMapping(path = "api/coaches")
public class CoachController {
    @Autowired
    private CoachService coachService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Coaches", description = "Get all Coaches", tags = {"coaches"})
    @GetMapping
    public Page<CoachResource> getAllCoaches(Pageable pageable){
        Page<Coach> coachPage = coachService.getAllCoaches(pageable);
        List<CoachResource> resources = coachPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Coach By Id", description = "Get Coach By Id", tags = {"coaches"})
    @GetMapping("/{coachId}")
    public CoachResource getCoachById(@PathVariable Long coachId){
        return convertToResource(coachService.getCoachById(coachId));
    }

    @Operation(summary = "Post Coach", description = "Coach Player", tags = {"coaches"})
    @PostMapping
    public CoachResource createCoach(@Valid @RequestBody SaveCoachResource resource){
        Coach coach = convertToEntity(resource);
        return convertToResource(coachService.createCoach(coach));
    }

    @Operation(summary = "Update Coach", description = "Update Coach", tags = {"coaches"})
    @PutMapping("/{coachId}")
    public CoachResource updatePlayer(@PathVariable long coachId, @Valid @RequestBody SaveCoachResource resource){
        Coach coach = convertToEntity(resource);
        return convertToResource(coachService.updateCoach(coachId, coach));
    }

    @Operation(summary = "Delete coach", description = "Delete Coach", tags = {"coaches"})
    @DeleteMapping("/{coachId}")
    public ResponseEntity<Coach> deleteCoach(@PathVariable long coachId){
        return coachService.deleteCoach(coachId);
    }

    @Operation(summary = "Login", description = "Login By Email and Password", tags = {"coaches"})
    @PostMapping("/login")
    public CoachResource login(@Valid @RequestBody SaveLoginResource resource){
        return convertToResource(coachService.login(resource.getEmail(), resource.getPassword()));
    }


    private Coach convertToEntity(SaveCoachResource resource) {
        return mapper.map(resource, Coach.class);
    }

    private CoachResource convertToResource(Coach entity){
        return mapper.map(entity, CoachResource.class);
    }


}
