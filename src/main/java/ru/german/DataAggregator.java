package ru.german;

import java.util.concurrent.CompletableFuture;

public class DataAggregator {
    private final Service service;

    public DataAggregator(Service service) {
        this.service = service;
    }

    public CompletableFuture<ProductInfo> aggregateProductInfo(String productName) throws InterruptedException {
        CompletableFuture<String> description = service.fetchDescription(productName)
                .exceptionally(ex -> "Нет данных");

        CompletableFuture<Double> price = service.fetchPrice(productName)
                .exceptionally(ex -> 0.0);

        CompletableFuture<Double> rating = service.fetchRating(productName)
                .exceptionally(ex -> 0.0);


        return CompletableFuture.allOf(description, price, rating)
                .thenApply(ignored -> {
                    ProductInfo product = new ProductInfo(productName);
                    product.setDescription(description.join());
                    product.setPrice(price.join());
                    product.setRatting(rating.join());
                    return product;
                });
    }
}
