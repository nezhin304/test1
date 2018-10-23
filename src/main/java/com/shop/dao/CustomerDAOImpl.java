package com.shop.dao;

import com.shop.entity.Customer;
import com.shop.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

    @Override
    public void create(Customer customer) {

        PreparedStatement statement = null;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.prepareStatement("INSERT INTO customers (name) VALUES (?)");
            statement.setString(1, customer.getName());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public long getId(Customer customer) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long id = 0;

        try (Connection connection = Pool.getConnection()) {

            statement = connection.prepareStatement("SELECT customer_id FROM customers WHERE name = ?");
            statement.setString(1, customer.getName());
            resultSet = statement.executeQuery();
            connection.commit();

            resultSet.next();
            id = resultSet.getLong(1);

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }

        return id;
    }
}
