package com.shop.dao;

import com.shop.entity.Product;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

public interface ProductDAO {

    void save(Product product);

    void delete(Product product);

    void deleteAll();

    Product getByCode(String code);

    Collection getByCategory(String category);

    Collection getCategories(Product product);

    long getId(Product product);

    Collection getAll();



}
