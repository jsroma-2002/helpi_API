package com.telegaming.helpi.domain.repository;

import com.telegaming.helpi.domain.model.TrainingMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingMaterialRepository extends JpaRepository<TrainingMaterial, Long> {

    public Page<TrainingMaterial> findByTrainingMaterialId(Long id, Pageable page);

}
