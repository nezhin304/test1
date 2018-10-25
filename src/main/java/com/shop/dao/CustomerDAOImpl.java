package com.shop.dao;

import com.shop.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl extends AbstractDAO implements CustomerDAO {

    private Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

    @Override
    public void create(Customer customer) {

        PreparedStatement statement = null;

        try (Connection connection = getConnection()) {

            statement = connection.prepareStatement("INSERT INTO customers (name) VALUES (?)");
            statement.setString(1, customer.getName());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {

            Helper.closeStatementResultSet(statement, null);
        }
    }

    @Override
    public long getId(Customer customer) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long id = 0;

        try (Connection connection = getConnection()) {

            statement = connection.prepareStatement("SELECT customer_id FROM customers WHERE name = ?");
            statement.setString(1, customer.getName());
            resultSet = statement.executeQuery();
            connection.commit();

            resultSet.next();
            id = resultSet.getLong(1);

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {

            Helper.closeStatementResultSet(statement, resultSet);
        }

        return id;
    }
}
