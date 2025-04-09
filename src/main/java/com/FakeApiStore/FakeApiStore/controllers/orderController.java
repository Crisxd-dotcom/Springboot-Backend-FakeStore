package com.FakeApiStore.FakeApiStore.controllers;

import com.FakeApiStore.FakeApiStore.models.productModel;
import com.FakeApiStore.FakeApiStore.models.orderModel;
import com.FakeApiStore.FakeApiStore.services.ordernServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class orderController {

    private final ordernServices ordernServices;

    public orderController(ordernServices orderService) {
        this.ordernServices = orderService;
    }

    @PostMapping("/create")
    public orderModel createOrder(@RequestBody List<Long> productIds) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products/";

        Map<Long, Integer> productCount = new HashMap<>();
        for (Long id : productIds) {
            productCount.put(id, productCount.getOrDefault(id, 0) + 1);
        }

        List<productModel.SimplifiedProduct> simplifiedProducts = new ArrayList<>();
        double total = 0;

        for (Map.Entry<Long, Integer> entry : productCount.entrySet()) {
            Long id = entry.getKey();
            Integer quantity = entry.getValue();

            productModel product = restTemplate.getForObject(url + id, productModel.class);
            if (product != null) {
                simplifiedProducts.add(new productModel.SimplifiedProduct(
                        product.getTitle(), product.getPrice(), quantity));
                total += product.getPrice() * quantity;
            }
        }

        orderModel order = new orderModel();
        order.setSimplifiedProducts(simplifiedProducts);
        order.setTotal(total);

        ordernServices.saveOrder(order.getOrderId(), order);

        return order;
    }
}
