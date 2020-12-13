package com.rationaleemotions;

import java.util.Arrays;
import java.util.List;

class WareHouse {

  static List<Product> catalog() {
    List<Product> products = Arrays.asList(
        new Product("shoe", 450.00f),
        new Product("shirt", 490.00f),
        new Product("bag", 50.00f),
        new Product("watch", 950.00f),
        new Product("phone cover", 75.00f),
        new Product("slippers", 98.00f),
        new Product("comics", 15.00f),
        new Product("belt", 165.00f),
        new Product("sun glass", 225.00f),
        new Product("tv stand", 325.00f)
    );
    return products;
  }

}
