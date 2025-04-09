package com.FakeApiStore.FakeApiStore.models;

import java.util.List;
import java.util.UUID;

public class orderModel {
    private String orderId;
    private List<productModel.SimplifiedProduct> simplifiedProducts;
    private double total;

    // Constructor
    public orderModel() {
        this.orderId = UUID.randomUUID().toString();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<productModel.SimplifiedProduct> getSimplifiedProducts() {
        return simplifiedProducts;
    }

    public void setSimplifiedProducts(List<productModel.SimplifiedProduct> simplifiedProducts) {
        this.simplifiedProducts = simplifiedProducts;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
