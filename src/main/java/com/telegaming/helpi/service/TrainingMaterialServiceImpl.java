package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.Game;
import com.telegaming.helpi.domain.model.TrainingMaterial;
import com.telegaming.helpi.domain.repository.GameRepository;
import com.telegaming.helpi.domain.repository.TrainingMaterialRepository;
import com.telegaming.helpi.domain.service.TrainingMaterialService;
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
public class TrainingMaterialServiceImpl implements TrainingMaterialService {

    @Autowired
    private TrainingMaterialRepository trainingMaterialRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Page<TrainingMaterial> getAllTrainingMaterials(Pageable pageable) {

        return trainingMaterialRepository.findAll(pageable);
    }

    @Override
    public TrainingMaterial getTrainingMaterialById(Long trainingMaterialId) {
        return trainingMaterialRepository.findById(trainingMaterialId)
                .orElseThrow(()->new ResourceNotFoundException("TrainingMaterial", "Id",trainingMaterialId));
    }

    @Override
    public Page<TrainingMaterial> getTrainingMaterialByGameId(Long gameId, Pageable pageable) {
        return gameRepository.findById(gameId).map(game -> {
            Set<TrainingMaterial> trainingMaterials = game.getTrainingMaterials();
            List<TrainingMaterial> trainingMaterialList = new ArrayList<>(trainingMaterials);
            return new PageImpl<>(trainingMaterialList, pageable, trainingMaterials.size());
        }).orElseThrow(()->new ResourceNotFoundException("Game","Id",gameId));
    }

    @Override
    public TrainingMaterial createTrainingMaterial(TrainingMaterial trainingMaterial, Long gameId) {

        Game game = gameRepository.findById(gameId)
                .orElseThrow(()->new ResourceNotFoundException("Game","Id",gameId));

        trainingMaterial.setGame(game);

        return trainingMaterialRepository.save(trainingMaterial);
    }

    @Override
    public TrainingMaterial updateTrainingMaterial(Long trainingMaterialId, TrainingMaterial trainingMaterialRequest) {

        TrainingMaterial trainingMaterial = trainingMaterialRepository.findById(trainingMaterialId)
                .orElseThrow(()->new ResourceNotFoundException("TrainingMaterial", "Id", trainingMaterialId));

        trainingMaterial.setTitle(trainingMaterialRequest.getTitle());
        trainingMaterial.setTrainingDescription(trainingMaterialRequest.getTrainingDescription());
        trainingMaterial.setTrainingCoverUri(trainingMaterialRequest.getTrainingCoverUri());

        return trainingMaterialRepository.save(trainingMaterial);

    }

    @Override
    public ResponseEntity<?> deleteTrainingMaterial(Long trainingMaterialId) {
        TrainingMaterial trainingMaterial = trainingMaterialRepository.findById(trainingMaterialId)
                .orElseThrow(()->new ResourceNotFoundException("TrainingMaterial", "Id", trainingMaterialId));

        trainingMaterialRepository.delete(trainingMaterial);

        return ResponseEntity.ok().build();
    }
}
