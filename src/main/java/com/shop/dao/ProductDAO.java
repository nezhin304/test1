package com.shop.dao;

import com.shop.entity.Category;
import com.shop.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    public void create(Product product) throws SQLException;

    public void update(Product product);

    public List<Product> getAll() throws SQLException;

    public void deleteAll() throws SQLException;

    public long createCategory(Category category) throws SQLException;

    public void linkProductCategory(long product_id, long category_id) throws SQLException;

}
