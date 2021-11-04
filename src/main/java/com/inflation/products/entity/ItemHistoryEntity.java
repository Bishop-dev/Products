package com.inflation.products.entity;

import com.inflation.products.entity.enums.City;
import com.inflation.products.entity.enums.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itemHistory")
public class ItemHistoryEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", referencedColumnName = "id")
  private ItemEntity item;

  @Column(name = "date_of_parsing")
  private Date dateOfParsing;

  // обычная цена
  @Column(name = "price")
  private double price;

  // цена по скидке сейчас
  @Column(name = "discount_price")
  private double discountPrice;

  @Column(name = "shop")
  @Enumerated(EnumType.STRING)
  private Shop shop;

  @Column(name = "city")
  @Enumerated(EnumType.STRING)
  private City city;

  // товар в наличии?
  @Column(name = "in_stock")
  private boolean inStock;

  @Column(name = "is_new_product")
  private boolean isNewProduct;
}
