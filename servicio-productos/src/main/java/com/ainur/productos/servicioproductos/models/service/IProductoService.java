package com.ainur.productos.servicioproductos.models.service;

import java.util.List;

import com.ainur.productos.servicioproductos.models.entity.Producto;

public interface IProductoService {

    public List<Producto> findAll();
    public Producto findById(Long id);
    
}
