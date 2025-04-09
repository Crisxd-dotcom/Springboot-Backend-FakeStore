package com.FakeApiStore.FakeApiStore.controllers;

import com.FakeApiStore.FakeApiStore.models.productModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class productsController {
    @GetMapping("/products")
    public productModel[] obtainProducts() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products";
        productModel[] products = restTemplate.getForObject(url, productModel[].class);
        return products;
    }
}
