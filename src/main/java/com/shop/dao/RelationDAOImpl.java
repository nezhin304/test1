package com.shop.dao;


import com.shop.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RelationDAOImpl implements RelationDAO{

    Logger logger = LoggerFactory.getLogger(RelationDAOImpl.class);

    @Override
    public void create(long product_id, long category_id) {

        PreparedStatement statement = null;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.
                    prepareStatement("INSERT INTO products_categories (product_id, category_id) VALUES (?,?)");
            statement.setLong(1, product_id);
            statement.setLong(2, category_id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }

    }
}
