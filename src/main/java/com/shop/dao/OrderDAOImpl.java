package com.shop.dao;

import com.shop.entity.Order;
import com.shop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl extends AbstractDAO implements OrderDAO {

    private Logger logger = LoggerFactory.getLogger(OrderDAO.class);

    @Override
    public void create(Order order) {

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        long customer_id = customerDAO.getId(order.getCustomer());
        PreparedStatement statement = null;
        ArrayList<Product> products = new ArrayList<>(order.getProducts());

        try (Connection connection = getConnection()) {

            for (Product product : products) {

                statement = connection.prepareStatement("INSERT INTO orders (customer_id, product_code) VALUES (?,?)");
                statement.setLong(1, customer_id);
                statement.setString(2, product.getCode());
                statement.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {

            Helper.closeStatementResultSet(statement, null);
        }
    }
}
