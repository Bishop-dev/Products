package com.inflation.products.repository;

import com.inflation.products.entity.TrademarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrademarkRepository extends JpaRepository<TrademarkEntity, String> {
}
