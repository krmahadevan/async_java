package com.rationaleemotions;

import java.util.Random;

public class CartAssistant {

  private static final Random random = new Random();

  public static boolean checkout(Product product) {
    Waits.sleep("Checking out " + product);
    if ((random.nextInt(10) % 3) == 0) {
      throw new IllegalArgumentException("Encountered problems when checking out " + product);
    }
    return true;
  }

}
