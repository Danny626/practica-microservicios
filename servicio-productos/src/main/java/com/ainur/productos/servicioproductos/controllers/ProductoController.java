package com.ainur.productos.servicioproductos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.ainur.productos.servicioproductos.models.entity.Producto;
import com.ainur.productos.servicioproductos.models.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    @Autowired
    private Environment env;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> Listar() {
        return productoService.findAll().stream().map(p -> {
            p.setPort(Integer.parseInt(this.env.getProperty("local.server.port")));
            return p;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) {
        Producto producto = this.productoService.findById(id);
        producto.setPort(Integer.parseInt(this.env.getProperty("local.server.port")));
        return producto;
    }
    
}
