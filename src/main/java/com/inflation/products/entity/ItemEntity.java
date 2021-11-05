package com.inflation.products.entity;

import com.inflation.products.converter.StringListConverter;
import com.inflation.products.entity.enums.MeasureUnits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "custom_id")
    private String customId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;

    @Column(name = "sku")
    private String sku;

    @Column(name = "title")
    private String title;

    @Column(name = "weight")
    private double weight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trademark_id", referencedColumnName = "id")
    private TrademarkEntity trademark;

    @Column(name = "shopId")
    private String shopId;

    @Column(name = "bundle")
    private int bundle;

    @Column(name = "unit")
    private String unit;
    // облагаемый акцизом?
    @Column(name = "excisable")
    private Boolean excisable;

    @Column(name = "fat")
    private double fat;

    @Convert(converter = StringListConverter.class)
    @Column(name = "ingredients", columnDefinition = "TEXT")
    private List<String> ingredients;

    @Column(name = "temperature_min")
    private int temperatureMin;

    @Column(name = "temperature_max")
    private int temperatureMax;

    @Column(name = "is_alcohol")
    private Boolean isAlcohol;

    @Column(name = "is_nictotine")
    private Boolean isNicotine;

    @Column(name = "units")
    @Enumerated(EnumType.STRING)
    private MeasureUnits units;

    @Column(name = "shelf_life")
    private double shelfLife;

    @Column(name = "shelf_life_units")
    @Enumerated(EnumType.STRING)
    private MeasureUnits shelfLifeUnits;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nutrition_id", referencedColumnName = "id")
    private NutritionEntity nutritionEntity;
}
