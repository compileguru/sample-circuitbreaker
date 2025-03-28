package com.compileguru.circuitbreaker.service;

import com.compileguru.circuitbreaker.client.ExternalServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ExternalServiceClient externalServiceClient;

    public ProductService(ExternalServiceClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackProduct")
    @Retry(name = "productServiceRetry")
    @RateLimiter(name = "productServiceRateLimiter")
    public String getProduct() {
        return externalServiceClient.getProduct();  // Call external API
    }

    // Fallback method for both Circuit Breaker & Retry
    public String fallbackProduct(Throwable e) {
        return "Fallback: Default product data (Service Unavailable)";
    }
}

