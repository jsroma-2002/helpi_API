package com.telegaming.helpi.domain.service;

import com.telegaming.helpi.domain.model.TrainingMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TrainingMaterialService {
    Page<TrainingMaterial> getAllTrainingMaterials(Pageable pageable);
    TrainingMaterial getTrainingMaterialById(Long trainingMaterialId);
    TrainingMaterial createTrainingMaterial(TrainingMaterial trainingMaterial);
    TrainingMaterial updateTrainingMaterial(Long trainingMaterialId, TrainingMaterial trainingMaterialRequest);
    ResponseEntity<?> deleteTrainingMaterial(Long trainingMaterialId);
}
