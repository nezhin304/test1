package com.shop.dao;

import com.shop.entity.Product;

import java.util.Collection;
import java.util.List;

public interface ProductDAO {

    void save(Product product);

    void delete(Product product);

    long getById(long id);

    Collection getByCategory(String category);

    Collection getCategories(Product product);

    Collection getAll();

}
