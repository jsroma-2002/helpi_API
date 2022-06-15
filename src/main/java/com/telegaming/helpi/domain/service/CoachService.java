package com.telegaming.helpi.domain.service;

import com.telegaming.helpi.domain.model.Coach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CoachService {
    Page<Coach> getAllCoaches(Pageable pageable);
    Coach getCoachById(Long coachId);
    Coach createCoach(Coach coach);
    Coach updateCoach(Long coachId, Coach coachRequest);
    ResponseEntity<?> deleteCoach(Long coachId);
    Coach login(String email, String password);
}
