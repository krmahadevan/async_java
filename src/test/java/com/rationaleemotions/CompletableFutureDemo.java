package com.rationaleemotions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newCachedThreadPool();
    List<CompletableFuture<Boolean>> all = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      CompletableFuture<Boolean> future = CompletableFuture
          .supplyAsync(SearchAssistant::search, executor)
          .thenApply(CartAssistant::checkout)
          .exceptionally(t -> {
            System.err.println("Problem. Root cause: " + t.getCause());
            return false;
          });

      all.add(future);
    }
    //This will cause everything to wait
    CompletableFuture<Void> result = CompletableFuture.allOf(all.toArray(new CompletableFuture[0]));

    CompletableFuture<List<Boolean>> resultList = result
        .thenApply(ea -> all.stream().map(CompletableFuture::join).collect(Collectors.toList()));
    System.err.println("::::: Completing checkout");
    resultList.get().forEach(System.err::println);
    executor.shutdown();
  }

}
