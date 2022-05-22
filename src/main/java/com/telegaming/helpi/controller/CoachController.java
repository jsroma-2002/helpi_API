package com.telegaming.helpi.controller;

import com.telegaming.helpi.domain.model.Coach;
import com.telegaming.helpi.domain.service.CoachService;
import com.telegaming.helpi.resource.coach.CoachResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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
    }
}
