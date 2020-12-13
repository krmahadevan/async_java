package com.rationaleemotions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class FuturesDemo {

  public static void main(String[] args) throws InterruptedException {
    List<Callable<Boolean>> tasks = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      tasks.add(() -> {
        Product product = SearchAssistant.search();
        return CartAssistant.checkout(product);
      });
    }
    ExecutorService service = Executors.newCachedThreadPool();
    List<Future<Boolean>> futures = service.invokeAll(tasks);
    service.shutdown();
    System.err.println("::::: Completing checkout");
    AtomicInteger counter = new AtomicInteger(1);
    for (Future<Boolean> future : futures) {
      try {
        System.err.println("Product " + future.get() + "[" + counter.getAndIncrement() + "]" + Thread.currentThread());
      } catch (ExecutionException e) {
        System.err.println("Problem. Root cause: " + e.getCause());
      }
    }
  }

}
