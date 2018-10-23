package com.shop.dao;

import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {

    Logger logger = LoggerFactory.getLogger(OrderDAO.class);

    @Override
    public void create(Order order) {

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        long customer_id = customerDAO.getId(order.getCustomer());

        PreparedStatement statement = null;


        try (Connection connection = Pool.getConnection()) {

            for (Product product : order.getProducts()) {

                statement = connection.prepareStatement("INSERT INTO orders (customer_id, product_code) VALUES (?,?)");
                statement.setLong(1, customer_id);
                statement.setString(2, product.getCode());
                statement.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
