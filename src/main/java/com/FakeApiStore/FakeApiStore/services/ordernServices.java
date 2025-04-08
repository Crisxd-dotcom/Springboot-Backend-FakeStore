package com.FakeApiStore.FakeApiStore.services;

import com.FakeApiStore.FakeApiStore.models.orderModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ordernServices {
    private final Map<String, orderModel> ordenesDB = new HashMap<>();

    public void guardarOrden(String id, orderModel orden) {
        ordenesDB.put(id, orden);
    }

    public orderModel obtenerOrden(String id) {
        return ordenesDB.get(id);
    }
}
