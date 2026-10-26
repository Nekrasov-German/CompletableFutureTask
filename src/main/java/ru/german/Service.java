package ru.german;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Service {
    private static final double RATTING = 4.7;
    private static final String DESCRIPTION = "Описание товара.";
    private static final double PRICE = 899.99;
    private static final String NAME = "Ноутбук";

    private final Random random = new Random();

    public CompletableFuture<Double> fetchPrice(String name) throws InterruptedException {
        if (NAME.equals(name)) {
            return CompletableFuture.supplyAsync(() -> {
                int delaySeconds = 1 + random.nextInt(3);
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(delaySeconds));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Sleep interrupted", e);
                }

                if (random.nextDouble() < 0.2) {
                    throw new RuntimeException("Случайный сбой в SlowService");
                }

                return PRICE;
            });
        } else {
            return null;
        }
    }

    public CompletableFuture<String> fetchDescription(String name) throws InterruptedException {
        if (NAME.equals(name)) {
            return CompletableFuture.supplyAsync(() -> {
                int delaySeconds = 1 + random.nextInt(3);
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(delaySeconds));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Sleep interrupted", e);
                }

                if (random.nextDouble() < 0.2) {
                    throw new RuntimeException("Случайный сбой в SlowService");
                }

                return DESCRIPTION;
            });
        } else {
            return null;
        }
    }

    public CompletableFuture<Double> fetchRating(String name) throws InterruptedException {
        if (NAME.equals(name)) {
            return CompletableFuture.supplyAsync(() -> {
                int delaySeconds = 1 + random.nextInt(3);
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(delaySeconds));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Sleep interrupted", e);
                }

                if (random.nextDouble() < 0.2) {
                    throw new RuntimeException("Случайный сбой в SlowService");
                }

                return RATTING;
            });
        } else {
            return null;
        }
    }
}
