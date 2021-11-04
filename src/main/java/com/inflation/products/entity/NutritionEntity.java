package com.inflation.products.entity;

import com.inflation.products.entity.enums.MeasureUnits;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/** Хранение состава продукта: калорийность, жиры, углеводы */
@Data
@Entity
public class NutritionEntity {
  @Column(name = "protein_amount")
  private double proteinAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "protein_units")
  private MeasureUnits proteinUnits;

  @Column(name = "fat_amount")
  private double fatAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "fat_units")
  private MeasureUnits fatUnits;

  @Column(name = "carbohydrates_amount")
  private double carbohydratesAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "carbohydrates_units")
  private MeasureUnits carbohydratesUnits;

  @Column(name = "energy_amount")
  private double energyAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "energy_units")
  private MeasureUnits energyUnits;
}
