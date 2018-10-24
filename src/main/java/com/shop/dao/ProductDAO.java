package com.shop.dao;

import com.shop.entity.Product;

import java.util.List;

public interface ProductDAO {

    void save(Product product);

    void delete(Product product);

    long getById(long id);

    List<Product> getByCategory(String category);

    List<Product> getAll();

}
