package com.ainur.productos.servicioproductos.controllers;

import java.util.List;

import com.ainur.productos.servicioproductos.models.entity.Producto;
import com.ainur.productos.servicioproductos.models.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> Listar() {
        return productoService.findAll();
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) {
        return productoService.findById(id);
    }
    
}
