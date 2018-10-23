package com.shop.dao;

import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

    @Override
    public void create(Product product) {

        RelationDAOImpl relationDAO = new RelationDAOImpl();
        ProductDAOImpl productDAO = new ProductDAOImpl();
        long product_id = productDAO.getId(product);
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        long category_id = 0;


        for (Category category : product.getCategories()) {

            try (Connection connection = Pool.getConnection()) {

                statement = connection.
                        prepareStatement("INSERT INTO categories (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, category.getName());
                statement.executeUpdate();
                resultSet = statement.getGeneratedKeys();
                connection.commit();
                resultSet.next();
                category_id = resultSet.getLong(1);

                relationDAO.create(product_id, category_id);

            } catch (SQLException e) {

                if (e.getMessage().contains("duplicate key value")) {

                    category_id = getId(category);

                } else {
                    logger.error(e.getMessage());
                }
            } finally

            {

                Helper.closeStatementResultSet(statement, resultSet);

            }

        }
    }


    @Override
    public void update(Category category) {

    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    private long getId(Category category) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long category_id = 0;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.
                    prepareStatement("SELECT MAX(category_id) FROM categories WHERE name = ?");
            statement.setString(1, category.getName());
            resultSet = statement.executeQuery();
            connection.commit();
            resultSet.next();
            category_id = resultSet.getLong(1);

        } catch (SQLException e) {

            logger.info(e.getMessage());

        } finally {

            Helper.closeStatementResultSet(statement, resultSet);
        }
        return category_id;
    }

}
