package com.telegaming.helpi.service;

import com.telegaming.helpi.domain.model.Coach;
import com.telegaming.helpi.domain.repository.CoachRepository;
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

    private static final String COACH = "Coach";

    @Override
    public Page<Coach> getAllCoaches(Pageable pageable){
        return coachRepository.findAll(pageable);
    }

    @Override
    public Coach getCoachById(Long coachId) {
        return coachRepository.findById(coachId)
                .orElseThrow(()->new ResourceNotFoundException(COACH, "Id", coachId));
    }

    @Override
    public Coach createCoach(Coach coach) {

        Boolean exist = coachRepository.existsByEmail(coach.getEmail());

        if (Boolean.TRUE.equals(exist)){
            throw new ResourceIncorrectData("El email ya esta en uso");
        } else {
            return coachRepository.save(coach);
        }
    }

    @Override
    public Coach updateCoach(Long coachId, Coach coachRequest) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(()->new ResourceNotFoundException(COACH,"Id", coachId));
        coach.setBirthDate(coachRequest.getBirthDate());
        coach.setName(coachRequest.getName());
        coach.setEmail(coachRequest.getEmail());
        coach.setPassword(coachRequest.getPassword());
        coach.setField(coachRequest.getField());
        coach.setCoachProfilePicture(coachRequest.getCoachProfilePicture());

        return coachRepository.save(coach);
    }

    @Override
    public ResponseEntity<Coach> deleteCoach(Long coachId) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(()->new ResourceNotFoundException(COACH, "Id", coachId));
        coachRepository.delete(coach);
        return ResponseEntity.ok().build();
    }

    @Override
    public Coach login(String email, String password) {
        return coachRepository.findByEmailAndPassword(email, password);
    }

}
