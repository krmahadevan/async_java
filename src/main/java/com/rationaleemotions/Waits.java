package com.rationaleemotions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Waits {
  private static final Random random = new Random();

  public static void sleep(String operation) {
    int seconds = random.nextInt(10);
    try {
      TimeUnit.SECONDS.sleep(seconds);
      System.err.println(operation + " on " + Thread.currentThread());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

}
