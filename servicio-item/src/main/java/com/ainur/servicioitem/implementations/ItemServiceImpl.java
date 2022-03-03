package com.ainur.servicioitem.implementations;

import java.util.List;
import java.util.stream.Collectors;

import com.ainur.servicioitem.clientes.IProductoClienteRest;
import com.ainur.servicioitem.models.Item;
import com.ainur.serviciocommons.models.Producto;
import com.ainur.servicioitem.service.IItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("serviceFeign")
@Primary
public class ItemServiceImpl implements IItemService {

    @Autowired
    private IProductoClienteRest clienteFeign;

    @Override
    public List<Item> findAll() {
        return this.clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(this.clienteFeign.detalle(id), cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        return this.clienteFeign.crear(producto);
    }

    @Override
    public Producto update(Producto producto, Long id) {
        return this.clienteFeign.editar(producto, id);
    }

    @Override
    public void delete(Long id) {
        this.clienteFeign.eliminar(id);
    }
    

}
