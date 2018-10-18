package com.shop.dao;

import com.shop.entity.Category;
import com.shop.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    public long create(Product product);

    public void update(Product product);

    public List<Product> getAll();

    public void deleteAll();

}
