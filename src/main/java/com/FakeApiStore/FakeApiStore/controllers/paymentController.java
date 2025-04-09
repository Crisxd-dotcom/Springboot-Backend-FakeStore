package com.FakeApiStore.FakeApiStore.controllers;

import com.FakeApiStore.FakeApiStore.models.orderModel;
import com.FakeApiStore.FakeApiStore.services.ordernServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class paymentController {

    private final ordernServices ordernServices;

    public paymentController(ordernServices ordernServices) {
        this.ordernServices = ordernServices;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> payOrder(@PathVariable String orderId,
                                                        @RequestBody Map<String, Double> body) {
        orderModel order = ordernServices.getOrder(orderId);

        Map<String, Object> response = new HashMap<>();

        if (order == null) {
            response.put("status", "error");
            response.put("Mensaje", "No se encontro la orden con ID: " + orderId);
            return ResponseEntity.badRequest().body(response);
        }

        Double paymentAmount = body.get("total");
        if (paymentAmount == null) {
            response.put("status", "error");
            response.put("Mensaje", "Favor ingrese el total a pagar");
            return ResponseEntity.badRequest().body(response);
        }

        if (paymentAmount < order.getTotal()) {
            response.put("status", "Rechazado");
            response.put("Mensaje", "Pago rechazado por falta de fondos");
            response.put("requiredTotal", order.getTotal());
            return ResponseEntity.ok(response);
        }

        response.put("status", "Aceptado");
        response.put("Mensaje", "Pago satisfactorio para orden: " + orderId);
        response.put("Total Pagado", paymentAmount);
        return ResponseEntity.ok(response);
    }
}
