package com.inflation.products.repository;

import com.inflation.products.entity.RawDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawDataRepository extends JpaRepository<RawDataEntity, String> {
}
