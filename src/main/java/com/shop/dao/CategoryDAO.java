package com.shop.dao;

import com.shop.entity.Category;

import java.util.List;

public interface CategoryDAO {

    public void create(Category category);

    public void update(Category category);

    public List<Category> getAll();

    public void deleteAll();
}
