package com.inflation.products.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

	/*
	String sku = product.getString("sku");
            String title = product.getString("title");
            double price = product.getDouble("price") / 100.0d;
            String country = product.getString("country");
            double weight = product.getDouble("weight");
            JSONObject producer = product.getJSONObject("producer");
            String trademark = producer.getString("trademark");
            String trademarkSlug = producer.getString("trademark_slug");
	 */

}
