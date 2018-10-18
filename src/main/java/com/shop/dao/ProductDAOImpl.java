package com.shop.dao;

import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);


    @Override
    public void create(Product product) throws SQLException {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> getAll() throws SQLException {
        return null;
    }

    @Override
    public void deleteAll() throws SQLException {

    }
}

