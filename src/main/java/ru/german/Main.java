package ru.german;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DataAggregator aggregator = new DataAggregator(new Service());

        CompletableFuture<ProductInfo> productInfo = aggregator.aggregateProductInfo("Ноутбук");

        ProductInfo productInfo1 = productInfo.join();

        System.out.println(productInfo1);
    }
}
