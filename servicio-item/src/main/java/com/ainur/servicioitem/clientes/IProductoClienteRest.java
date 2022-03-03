package com.ainur.servicioitem.clientes;

import java.util.List;

import com.ainur.serviciocommons.models.Producto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servicio-productos")
public interface IProductoClienteRest {

    @GetMapping("/listar")
    public List<Producto> listar();

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id);

    @PostMapping("/crear")
    public Producto crear(@RequestBody Producto producto);

    @PutMapping("/editar/{id}")
    public Producto editar(@RequestBody Producto producto, @PathVariable Long id);

    @PutMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id);
    
}
