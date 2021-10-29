package com.inflation.products.repository;

import com.inflation.products.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, String> {
}