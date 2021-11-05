package com.inflation.products.repository;

import com.inflation.products.entity.NutritionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionRepository extends JpaRepository<NutritionEntity, String> {
}
