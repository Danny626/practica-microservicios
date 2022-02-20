package com.ainur.servicioitem.service;

import java.util.List;

import com.ainur.servicioitem.models.Item;

public interface IItemService {

    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
    
}
