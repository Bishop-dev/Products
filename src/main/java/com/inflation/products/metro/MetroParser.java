package com.inflation.products.metro;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.inflation.products.entity.enums.City;
import com.inflation.products.factory.WebClientFactory;
import com.inflation.products.model.ParsedItemModel;
import com.inflation.products.service.ParsedItemHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class MetroParser {

  @Autowired private WebClientFactory webClientFactory;
  @Autowired private ParsedItemHandlerService parsedItemHandlerService;

  @Scheduled(fixedDelay = 10000)
  public void start() {
    try (final WebClient client = webClientFactory.createProxyClient()) {
      // step 1 - get categories
      // https://stores-api.zakaz.ua/stores/48215632/categories/
      Charset charset = StandardCharsets.UTF_8;
      String shopId = "48215632";
      client.addRequestHeader("Accept-Language", "ru");
      client.addRequestHeader("Accept-Charset", "utf-8");
      Page categoriesPage =
          client.getPage("https://stores-api.zakaz.ua/stores/" + shopId + "/categories/");
      String categoriesJsonStr = categoriesPage.getWebResponse().getContentAsString(charset);
      List<JSONObject> categoriesJson = shuffle(new JSONArray(categoriesJsonStr));
      log.debug("{} Categories received", categoriesJson.size());
      categoriesJson.stream()
          .map(category -> category.getJSONArray("children"))
          .filter(subCategories -> !subCategories.isEmpty())
          .map(this::shuffle)
          .flatMap(List::stream)
          .forEach(
              subCategory -> {
                String subCategoryId = subCategory.getString("id");
                // https://stores-api.zakaz.ua/stores/48215632/categories/bread-metro/products/
                Page productsPage =
                    fetchPage(
                        client,
                        "https://stores-api.zakaz.ua/stores/"
                            + shopId
                            + "/categories/"
                            + subCategoryId
                            + "/products/?page=1");
                String productsJson = productsPage.getWebResponse().getContentAsString(charset);
                JSONObject products = new JSONObject(productsJson);
                int count = products.getInt("count");
                JSONArray results = products.getJSONArray("results");
                int itemsParsed = 0;
                while (itemsParsed < count) {
                  for (int k = 0; k < results.length(); k++) {
                    JSONObject product = results.getJSONObject(k);
                    JSONObject producer = product.getJSONObject("producer");
                    ParsedItemModel item = new ParsedItemModel();
                    item.setSku(product.getString("sku"));
                    item.setTitle(product.getString("title"));
                    item.setPrice(product.getDouble("price") / 100.0d);
                    item.setCountry(product.getString("country"));
                    item.setWeight(product.getDouble("weight"));
                    item.setTrademark(producer.getString("trademark"));
                    item.setTrademarkSlug(producer.getString("trademark_slug"));
                    item.setBundle(producer.optInt("bundle", -1));
                    item.setUnit(product.getString("unit"));
                    item.setShopId(shopId);
                    item.setCity(City.KHARKIV);
                    parsedItemHandlerService.save(item);
                    itemsParsed++;
                  }
                }
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Page fetchPage(WebClient client, String url) {
    try {
      return client.getPage(url);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private List<JSONObject> shuffle(JSONArray array) {
    List<JSONObject> items = new ArrayList<>();
    for (int i = 0; i < array.length(); i++) {
      items.add(array.getJSONObject(i));
    }
    Collections.shuffle(items);
    return items;
  }
}
