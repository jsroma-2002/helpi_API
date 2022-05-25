package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.Coach;
import com.telegaming.helpi.domain.model.TrainingMaterial;
import com.telegaming.helpi.domain.repository.CoachRepository;
import com.telegaming.helpi.domain.repository.GameRepository;
import com.telegaming.helpi.domain.repository.TrainingMaterialRepository;
import com.telegaming.helpi.domain.service.CoachService;
import com.telegaming.helpi.exception.ResourceIncorrectData;
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
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private GameRepository gameRepository;

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
    public Page<Coach> getCoachesByGameId(Long gameId, Pageable pageable) {
        return gameRepository.findById(gameId).map(game -> {
            Set<Coach> coaches = game.getCoaches();
            List<Coach> coachList = new ArrayList<>(coaches);
            return new PageImpl<>(coachList, pageable, coaches.size());
        }).orElseThrow(()->new ResourceNotFoundException("Game","Id",gameId));
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
    public ResponseEntity<?> deleteCoach(Long coachId) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(()->new ResourceNotFoundException("Coach", "Id", coachId));
        coachRepository.delete(coach);
        return ResponseEntity.ok().build();
    }


}
