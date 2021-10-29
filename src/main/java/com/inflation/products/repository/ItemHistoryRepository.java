package com.inflation.products.repository;

import com.inflation.products.entity.ItemHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemHistoryRepository extends JpaRepository<ItemHistoryEntity, String> {
}
