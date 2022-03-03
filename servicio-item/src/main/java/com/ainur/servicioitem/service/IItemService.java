package com.ainur.servicioitem.service;

import java.util.List;

import com.ainur.servicioitem.models.Item;
import com.ainur.serviciocommons.models.Producto;

public interface IItemService {

    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
    public Producto save(Producto producto);
    public Producto update(Producto producto, Long id);
    public void delete(Long id);
    
}
