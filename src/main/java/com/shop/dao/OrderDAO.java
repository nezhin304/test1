package com.shop.dao;

import com.shop.entity.Order;

import java.util.Collection;

public interface OrderDAO {

    void create(Order order);

    Collection getAll();
}
