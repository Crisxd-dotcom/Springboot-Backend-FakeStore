package com.FakeApiStore.FakeApiStore.controllers;

import com.FakeApiStore.FakeApiStore.models.productosModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class productosController {
    @GetMapping("/productos")
    public productosModel[] obtenerProductos() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products";
        productosModel[] productos = restTemplate.getForObject(url, productosModel[].class);
        return productos;
    }
}
