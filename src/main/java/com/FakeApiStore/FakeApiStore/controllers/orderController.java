package com.FakeApiStore.FakeApiStore.controllers;

import com.FakeApiStore.FakeApiStore.models.productosModel;
import com.FakeApiStore.FakeApiStore.models.orderModel;
import com.FakeApiStore.FakeApiStore.services.ordernServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/ordenes")
public class orderController {

    private final ordernServices ordenService;

    public orderController(ordernServices ordenService) {
        this.ordenService = ordenService;
    }

    @PostMapping("/crear")
    public orderModel crearOrden(@RequestBody List<Long> idsProductos) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products/";

        Map<Long, Integer> contadorProductos = new HashMap<>();
        for (Long id : idsProductos) {
            contadorProductos.put(id, contadorProductos.getOrDefault(id, 0) + 1);
        }

        List<productosModel.ProductoSimplificado> productosSimplificados = new ArrayList<>();
        double total = 0;

        for (Map.Entry<Long, Integer> entry : contadorProductos.entrySet()) {
            Long id = entry.getKey();
            Integer cantidad = entry.getValue();

            productosModel producto = restTemplate.getForObject(url + id, productosModel.class);
            if (producto != null) {
                productosSimplificados.add(new productosModel.ProductoSimplificado(
                        producto.getTitle(), producto.getPrice(), cantidad));
                total += producto.getPrice() * cantidad;
            }
        }

        orderModel orden = new orderModel();
        orden.setProductosSimplificados(productosSimplificados);
        orden.setTotal(total);

        // Guardar en el servicio compartido
        ordenService.guardarOrden(orden.getIdOrden(), orden);

        return orden;
    }
}
