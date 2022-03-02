package com.ainur.productos.servicioproductos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.ainur.productos.servicioproductos.models.entity.Producto;
import com.ainur.productos.servicioproductos.models.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
public class ProductoController {

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> Listar() {
        return productoService.findAll().stream().map(p -> {
            p.setPort(Integer.parseInt(this.env.getProperty("local.server.port")));
            // p.setPort(port);
            return p;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) {
        Producto producto = this.productoService.findById(id);
        producto.setPort(Integer.parseInt(this.env.getProperty("local.server.port")));
        // producto.setPort(port);
        return producto;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto) {
        
        return this.productoService.save(producto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto, @PathVariable Long id) {
        
        Producto productoDb = this.productoService.findById(id);
        productoDb.setNombre(producto.getNombre());
        productoDb.setPrecio(producto.getPrecio());

        return this.productoService.save(producto);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {

        this.productoService.deleteById(id);
    }
    
    
}
