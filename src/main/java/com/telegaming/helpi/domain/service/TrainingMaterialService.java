package com.telegaming.helpi.domain.service;

import com.telegaming.helpi.domain.model.TrainingMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TrainingMaterialService {
    Page<TrainingMaterial> getAllTrainingMaterials(Pageable pageable);
    TrainingMaterial getTrainingMaterialById(Long trainingMaterialId);
    Page<TrainingMaterial> getTrainingMaterialByGameId(Long gameId, Pageable pageable);
    TrainingMaterial createTrainingMaterial(TrainingMaterial trainingMaterial, Long gameId);
    TrainingMaterial updateTrainingMaterial(Long trainingMaterialId, TrainingMaterial trainingMaterialRequest);
    ResponseEntity<?> deleteTrainingMaterial(Long trainingMaterialId);
}
