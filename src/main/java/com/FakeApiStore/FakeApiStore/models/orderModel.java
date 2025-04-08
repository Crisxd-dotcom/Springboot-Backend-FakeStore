package com.FakeApiStore.FakeApiStore.models;

import java.util.List;
import java.util.UUID;

public class orderModel {
    private String idOrden;
    private List<productosModel.ProductoSimplificado> productosSimplificados;
    private double total;

    // Constructor
    public orderModel() {
        // Generar un UUID aleatorio para la orden
        this.idOrden = UUID.randomUUID().toString();
    }

    // Getters y setters
    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public List<productosModel.ProductoSimplificado> getProductosSimplificados() {
        return productosSimplificados;
    }

    public void setProductosSimplificados(List<productosModel.ProductoSimplificado> productosSimplificados) {
        this.productosSimplificados = productosSimplificados;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
