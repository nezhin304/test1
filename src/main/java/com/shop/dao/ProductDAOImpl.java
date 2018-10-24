package com.shop.dao;

import com.shop.entity.Product;
import com.shop.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    @Override
    public void save(Product product) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long productId = 0;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.
                    prepareStatement("INSERT INTO products (name, code) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getCode());
            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            productId = resultSet.getLong(1);

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {

            Helper.closeStatementResultSet(statement, null);
        }

        for (String category : product.getCategories()) {

            long categoryId = saveCategory(category);

            try (Connection connection = Pool.getConnection()) {

                statement = connection.prepareStatement("INSERT INTO products_categories (product_id, category_id) VALUES (?,?)");
                statement.setLong(1, productId);
                statement.setLong(2, categoryId);
                statement.execute();
                connection.commit();

            } catch (SQLException e) {
                logger.error(e.getMessage());
            } finally {
                Helper.closeStatementResultSet(statement, null);
            }
        }
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public long getById(long id) {
        return 0;
    }

    @Override
    public List<Product> getByCategory(String category) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    private long saveCategory(String category) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long categoryId = 0;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.prepareStatement("INSERT INTO categories (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category);
            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            categoryId = resultSet.getLong(1);

        } catch (SQLException e) {

            if (e.getMessage().contains("duplicate key value")) {
                categoryId = getCategiryId(category);
            } else {
                logger.error(e.getMessage());
            }

        } finally {
            Helper.closeStatementResultSet(statement, resultSet);
        }

        return categoryId;
    }


    private long getCategiryId(String category) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long categoryId = 0;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.
                    prepareStatement("SELECT MAX(category_id) FROM categories WHERE name = ?");
            statement.setString(1, category);
            resultSet = statement.executeQuery();
            connection.commit();
            resultSet.next();
            categoryId = resultSet.getLong(1);

        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            Helper.closeStatementResultSet(statement, resultSet);
        }

        return categoryId;
    }
}


