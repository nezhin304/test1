package com.shop.dao;

import com.shop.entity.Category;
import com.shop.entity.Product;

import java.util.List;

public interface CategoryDAO {

    public void create(Product product);

    public void update(Category category);

    public List<Category> getAll();

    public void deleteAll();
}
