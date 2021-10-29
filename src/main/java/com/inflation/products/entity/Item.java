package com.inflation.products.entity;

import lombok.Data;

//import javax.persistence.Entity;

@Data
//@Entity
//@Table(name = "")
public class Item {

	private String sku;
	private String title;
	private double price;
	private String country;
	private double weight;
	private String trademark;
	private String trademarkSlug;
	private String shopId;
	private String city;
	private int bundle;
	private String unit;
	private Vendor vendor;

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
