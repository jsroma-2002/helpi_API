package com.telegaming.helpi.controller;

import com.telegaming.helpi.domain.model.TrainingMaterial;
import com.telegaming.helpi.domain.service.TrainingMaterialService;
import com.telegaming.helpi.resource.training.TrainingMaterialResource;
import com.telegaming.helpi.resource.training.SaveTrainingMaterialResource;
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
@RequestMapping(path = "api/trainingMaterials")
public class TrainingMaterialController {
    
    @Autowired
    private TrainingMaterialService trainingMaterialService;
    
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get TrainingMaterials", description = "Get all TrainingMaterials", tags = {"trainingMaterials"})
    @GetMapping
    public Page<TrainingMaterialResource> getAllTrainingMaterials(Pageable pageable){
        Page<TrainingMaterial> trainingMaterialPage = trainingMaterialService.getAllTrainingMaterials(pageable);
        List<TrainingMaterialResource> resources = trainingMaterialPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get TrainingMaterial By Id", description = "Get TrainingMaterial By Id", tags = {"trainingMaterials"})
    @GetMapping("/{trainingMaterialId}")
    public TrainingMaterialResource getTrainingMaterialById(@PathVariable Long trainingMaterialId){
        return convertToResource(trainingMaterialService.getTrainingMaterialById(trainingMaterialId));
    }

    @Operation(summary = "Get TrainingMaterial By Game Id", description = "Get TrainingMaterial By Game Id", tags = {"trainingMaterials"})
    @GetMapping("/game/{gameId}/trainings")
    public Page<TrainingMaterialResource> getTrainingMaterialByGameId(Pageable pageable, @PathVariable Long gameId){
        Page<TrainingMaterial> trainingMaterialPage = trainingMaterialService.getTrainingMaterialByGameId(gameId, pageable);
        List<TrainingMaterialResource> resources = trainingMaterialPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get TrainingMaterial By Coach Id", description = "Get TrainingMaterial By Coach Id", tags = {"trainingMaterials"})
    @GetMapping("/coach/{coachId}/trainings")
    public Page<TrainingMaterialResource> getTrainingMaterialByCoachId(Pageable pageable, @PathVariable Long coachId){
        Page<TrainingMaterial> trainingMaterialPage = trainingMaterialService.getTrainingMaterialByGameId(coachId, pageable);
        List<TrainingMaterialResource> resources = trainingMaterialPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get TrainingMaterial By Player Id", description = "Get TrainingMaterial By Player Id", tags = {"trainingMaterials"})
    @GetMapping("/player/{playerId}")
    public Page<TrainingMaterialResource> getTrainingMaterialByPlayerId(Pageable pageable, @PathVariable Long playerId){
        Page<TrainingMaterial> trainingMaterialPage = trainingMaterialService.getTrainingMaterialByPlayerId(playerId, pageable);
        List<TrainingMaterialResource> resources = trainingMaterialPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Post TrainingMaterial", description = "Post TrainingMaterial", tags = {"trainingMaterials"})
    @PostMapping("/{gameId}")
    public TrainingMaterialResource createTrainingMaterial(@Valid @RequestBody SaveTrainingMaterialResource resource, @PathVariable Long gameId){
        TrainingMaterial trainingMaterial = convertToEntity(resource);
        return convertToResource(trainingMaterialService.createTrainingMaterial(trainingMaterial, gameId));
    }

    @Operation(summary = "Update TrainingMaterial", description = "Update TrainingMaterial", tags = {"trainingMaterials"})
    @PutMapping("/{trainingMaterialId}")
    public TrainingMaterialResource updateTrainingMaterial(@PathVariable long trainingMaterialId, @Valid @RequestBody SaveTrainingMaterialResource resource){
        TrainingMaterial trainingMaterial = convertToEntity(resource);
        return convertToResource(trainingMaterialService.updateTrainingMaterial(trainingMaterialId, trainingMaterial));
    }

    @Operation(summary = "Delete TrainingMaterial", description = "Delete TrainingMaterial", tags = {"trainingMaterials"})
    @DeleteMapping("/{trainingMaterialId}")
    public ResponseEntity<TrainingMaterial> deleteTrainingMaterial(@PathVariable long trainingMaterialId){
        return trainingMaterialService.deleteTrainingMaterial(trainingMaterialId);
    }


    private TrainingMaterial convertToEntity(SaveTrainingMaterialResource resource) {
        return mapper.map(resource, TrainingMaterial.class);
    }

    private TrainingMaterialResource convertToResource(TrainingMaterial entity){
        return mapper.map(entity, TrainingMaterialResource.class);
    }
}
