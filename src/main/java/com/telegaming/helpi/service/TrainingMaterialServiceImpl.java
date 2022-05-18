package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.TrainingMaterial;
import com.telegaming.helpi.domain.repository.TrainingMaterialRepository;
import com.telegaming.helpi.domain.service.TrainingMaterialService;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrainingMaterialServiceImpl implements TrainingMaterialService {

    @Autowired
    private TrainingMaterialRepository trainingMaterialRepository;


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
    public TrainingMaterial createTrainingMaterial(TrainingMaterial trainingMaterial) {

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
