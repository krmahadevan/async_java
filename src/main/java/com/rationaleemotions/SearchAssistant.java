package com.rationaleemotions;

import java.util.List;
import java.util.Random;

public class SearchAssistant {

  private static final Random random = new Random();

  public static Product search() {
    List<Product> products = WareHouse.catalog();
    int size = products.size();
    Waits.sleep("Search catalog");
    int index = random.nextInt(size);
    return products.get(index);
  }
}
