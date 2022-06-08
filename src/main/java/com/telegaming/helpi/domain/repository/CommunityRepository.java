package com.telegaming.helpi.domain.repository;

import com.telegaming.helpi.domain.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

}
