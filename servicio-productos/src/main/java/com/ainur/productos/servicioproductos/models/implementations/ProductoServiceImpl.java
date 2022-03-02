package com.ainur.productos.servicioproductos.models.implementations;

import java.util.List;

import com.ainur.productos.servicioproductos.models.entity.Producto;
import com.ainur.productos.servicioproductos.models.repository.IProductoRepository;
import com.ainur.productos.servicioproductos.models.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return this.productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.productoRepository.deleteById(id);
        
    }
    
}
