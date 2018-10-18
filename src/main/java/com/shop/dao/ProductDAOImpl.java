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

        long product_id = 0;

        try (Connection connection = Pool.getConnection()) {

            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO products (name, code) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getCode());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            connection.commit();
            resultSet.next();
            product_id = resultSet.getLong(1);
            statement.close();

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }


        for (Category cat : product.getCategories()) {

            long category_id = createCategory(cat);

            linkProductCategory(product_id, category_id);

        }
    }

    @Override
    public long createCategory(Category category) throws SQLException {

        long category_id = 0;

        try (Connection connection = Pool.getConnection()) {

            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO categories (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            connection.commit();
            resultSet.next();
            category_id = resultSet.getLong(1);
            statement.close();

        } catch (SQLException e) {

            if ((e.getErrorCode()) == 0) {

                try (Connection connection = Pool.getConnection()) {

                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT max(category_id) FROM categories WHERE name = ?"
                    );
                    statement.setString(1, category.getName());
                    ResultSet resultSet1 = statement.executeQuery();
                    connection.commit();
                    resultSet1.next();
                    category_id = resultSet1.getLong(1);
                    statement.close();

                } catch (SQLException e1) {
                    throw new SQLException(e.getMessage(), e);
                }

            } else {
                throw new SQLException(e.getMessage(), e);
            }
        }

        if (category_id == 0) {
            logger.error("In method createCategory category_id == 0");
        }

        return category_id;
    }

    @Override
    public void linkProductCategory(long product_id, long category_id) throws SQLException {

        try (Connection connection = Pool.getConnection()) {

            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO products_categories (product_id, category_id) VALUES (?,?)");
            statement.setLong(1, product_id);
            statement.setLong(2, category_id);
            statement.execute();
            connection.commit();
            statement.close();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteAll() {

        final String DELETE = "DELETE FROM products";

        try (Connection connection = Pool.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.execute();
            connection.commit();
            statement.close();

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}

