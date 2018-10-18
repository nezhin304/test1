package com.shop.dao;

import com.shop.entity.Product;
import com.shop.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    @Override
    public long create(Product product) {

        PreparedStatement statement = null;
        long product_id = 0;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.
                    prepareStatement("INSERT INTO products (name, code) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getCode());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            connection.commit();
            resultSet.next();
            product_id = resultSet.getLong(1);

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return product_id;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> getAll(){
        return null;
    }

    @Override
    public void deleteAll() {

    }
}

