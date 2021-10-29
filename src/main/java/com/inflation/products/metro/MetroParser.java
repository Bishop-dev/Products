package com.inflation.products.metro;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.inflation.products.entity.Item;
import com.inflation.products.entity.Vendor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
public class MetroParser {



  @Scheduled(fixedDelay = 10000)
  public void start() {
    try (WebClient client = new WebClient(BrowserVersion.CHROME, "proxyIp", 1234)) {
      DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) client.getCredentialsProvider();
      credentialsProvider.addCredentials("proxyLogin", "proxyPassword");
      // step 1 - get categories
      // https://stores-api.zakaz.ua/stores/48215632/categories/
      Charset charset = StandardCharsets.UTF_8;
      String shopId = "48215632";
      client.addRequestHeader("Accept-Language", "ru");
      client.addRequestHeader("Accept-Charset", "utf-8");
      Page categoriesPage = client.getPage("https://stores-api.zakaz.ua/stores/" + shopId + "/categories/");
      String categoriesJsonStr = categoriesPage.getWebResponse().getContentAsString(charset);
      JSONArray categoriesJson = new JSONArray(categoriesJsonStr);
      for (int i = 0; i < categoriesJson.length(); i++) {
        JSONObject category = categoriesJson.getJSONObject(i);
        JSONArray subCategories = category.getJSONArray("children");
        if (subCategories.isEmpty()) {
          continue;
        }
        for (int j = 0; j < subCategories.length(); j++) {
          JSONObject subCategory = subCategories.getJSONObject(j);
          String subCategoryId = subCategory.getString("id");
          // https://stores-api.zakaz.ua/stores/48215632/categories/bread-metro/products/
          Page productsPage =
              client.getPage(
                  "https://stores-api.zakaz.ua/stores/" + shopId + "/categories/"
                      + subCategoryId
                      + "/products/?page=1");
          String productsJson = productsPage.getWebResponse().getContentAsString(charset);
          JSONObject products = new JSONObject(productsJson);
          int count = products.getInt("count");
          JSONArray results = products.getJSONArray("results");
          for (int k = 0; k < results.length(); k++) {
            JSONObject product = results.getJSONObject(k);
            String sku = product.getString("sku");
            String title = product.getString("title");
            double price = product.getDouble("price") / 100.0d;
            String country = product.getString("country");
            double weight = product.getDouble("weight");
            JSONObject producer = product.getJSONObject("producer");
            String trademark = producer.getString("trademark");
            String trademarkSlug = producer.getString("trademark_slug");
            int bundle = producer.getInt("bundle");
            String unit = product.getString("unit");

            Item item = new Item();
            item.setSku(sku);
            item.setCity("Kharkiv");
            item.setShopId(shopId);
            item.setVendor(Vendor.METRO);
            item.setTitle(title);
            item.setPrice(price);
            item.setCountry(country);
            item.setWeight(weight);
            item.setTrademark(trademark);
            item.setTrademarkSlug(trademarkSlug);
            item.setBundle(bundle);
            item.setUnit(unit);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
