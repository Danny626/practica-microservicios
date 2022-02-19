package com.ainur.productos.servicioproductos.models.repository;

import com.ainur.productos.servicioproductos.models.entity.Producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
    
}
