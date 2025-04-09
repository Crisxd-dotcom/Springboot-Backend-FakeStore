package com.FakeApiStore.FakeApiStore.services;

import com.FakeApiStore.FakeApiStore.models.orderModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ordernServices {
    private final Map<String, orderModel> ordersDB = new HashMap<>();

    public void saveOrder(String id, orderModel order) {
        ordersDB.put(id, order);
    }

    public orderModel getOrder(String id) {
        return ordersDB.get(id);
    }
}
