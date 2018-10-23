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
    public void create(Product product) {

        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        PreparedStatement statement = null;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.
                    prepareStatement("INSERT INTO products (name, code) VALUES (?,?)");
            statement.setString(1, product.getName());
            statement.setString(2, product.getCode());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {

            Helper.closeStatementResultSet(statement, null);
        }


        categoryDAO.create(product);

    }

    @Override
    public long getId(Product product) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long product_id = 0;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.
                    prepareStatement("SELECT MAX(product_id) FROM products WHERE code = ?");
            statement.setString(1, product.getCode());
            resultSet = statement.executeQuery();
            connection.commit();
            resultSet.next();
            product_id = resultSet.getLong(1);

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {

            Helper.closeStatementResultSet(statement, resultSet);
        }

        return product_id;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}

