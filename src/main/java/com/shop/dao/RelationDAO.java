package com.shop.dao;

import com.shop.entity.Category;
import com.shop.entity.Product;

public interface RelationDAO {

    public void create(long product_id, long category_id);

}
