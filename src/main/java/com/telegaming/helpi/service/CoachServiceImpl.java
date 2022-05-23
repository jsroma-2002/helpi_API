package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.Coach;
import com.telegaming.helpi.domain.model.TrainingMaterial;
import com.telegaming.helpi.domain.repository.CoachRepository;
import com.telegaming.helpi.domain.repository.TrainingMaterialRepository;
import com.telegaming.helpi.domain.service.CoachService;
import com.telegaming.helpi.exception.ResourceIncorrectData;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private TrainingMaterialRepository trainingMaterialRepository;

    @Override
    public Page<Coach> getAllCoaches(Pageable pageable){
        return coachRepository.findAll(pageable);
    }

    @Override
    public Coach getCoachById(Long coachId) {
        return coachRepository.findById(coachId)
                .orElseThrow(()->new ResourceNotFoundException("Coach", "Id", coachId));
    }

    @Override
    public Coach createCoach(Coach coach) {
        if (coachRepository.existsByEmail(coach.getEmail())){
            throw new ResourceIncorrectData("El email ya esta en uso");
        }
        return coachRepository.save(coach);
    }

    @Override
    public Coach updateCoach(Long coachId, Coach coachRequest) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(()->new ResourceNotFoundException("Coach","Id", coachId));
        coach.setBirthDate(coachRequest.getBirthDate());
        coach.setName(coachRequest.getName());
        coach.setEmail(coachRequest.getEmail());
        coach.setPassword(coachRequest.getPassword());

        return coachRepository.save(coach);
    }

    @Override
    public Coach postTrainingMaterial(Long coachId, Long trainingId) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(()->new ResourceNotFoundException("Coach","Id", coachId));

        TrainingMaterial trainingMaterial = trainingMaterialRepository.findById(trainingId)
                .orElseThrow(()->new ResourceNotFoundException("Training","Id", trainingId));

        if (!coach.getOwnedTrainingMaterials().contains(trainingMaterial)){
            coach.postTrainingMaterial(trainingMaterial);
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCoach(Long coachId) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(()->new ResourceNotFoundException("Coach", "Id", coachId));
        coachRepository.delete(coach);
        return ResponseEntity.ok().build();
    }


}
