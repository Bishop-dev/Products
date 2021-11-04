package com.inflation.products.model;

import com.inflation.products.entity.enums.City;
import com.inflation.products.entity.enums.MeasureUnits;
import lombok.Data;

@Data
public class ParsedItemModel {
  private String sku;
  private String title;
  private double price;
  private double discountPrice;
  private String country;
  private double weight;
  private String trademark;
  private String trademarkSlug;
  private int bundle;
  private String unit;
  private City city;
  private String shopId;
  private Boolean excisable;
  private String ingredients;
  private int temperatureMin;
  private int temperatureMax;
  private Boolean isAlcohol;
  private Boolean isNicotine;
  private MeasureUnits units;
  private double shelfLife;
  private MeasureUnits shelfLifeUnits;
  private String description;
  private boolean inStock;
  private boolean isNewProduct;
}
