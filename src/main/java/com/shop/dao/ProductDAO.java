package com.shop.dao;

import com.shop.entity.Product;

import java.util.List;

public interface ProductDAO {

    void create(Product product);

    long getId(Product product);

    void update(Product product);

    List<Product> getAll();

    void deleteAll();

}
