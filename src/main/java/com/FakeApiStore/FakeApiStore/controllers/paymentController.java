package com.FakeApiStore.FakeApiStore.controllers;

import com.FakeApiStore.FakeApiStore.models.orderModel;
import com.FakeApiStore.FakeApiStore.services.ordernServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class paymentController {

    private final ordernServices ordenService;

    public paymentController(ordernServices ordenService) {
        this.ordenService = ordenService;
    }

    @PostMapping("/{idOrden}")
    public String pagarOrden(@PathVariable String idOrden) {
        orderModel orden = ordenService.obtenerOrden(idOrden);

        if (orden == null) {
            return "Error: Orden no encontrada con ID: " + idOrden;
        }

        return "Pago realizado exitosamente para la orden con ID: " + idOrden + ". Total: $" + orden.getTotal();
    }
}
