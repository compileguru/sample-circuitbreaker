package com.compileguru.circuitbreaker.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
//To test failure and retry mechanism uncomment the line below
@FeignClient(name = "external-service", url = "https://dummyjson.com")
//@FeignClient(name = "external-service", url = "https://localhost:9999")
public interface ExternalServiceClient {
    @GetMapping("/products/1")  // Simulating an external API call
    String getProduct();
}
