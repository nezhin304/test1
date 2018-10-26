package com.shop.dao;

import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.instance.CustomerDAOInstance;
import com.shop.instance.ProductDAOInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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

    @Override
    public Collection getAll() {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Collection orders = new ArrayList();
        CustomerDAO customerDAO = CustomerDAOInstance.getInstance();
        ProductDAO productDAO = ProductDAOInstance.getInstance();

        try (Connection connection = getConnection()) {

            statement = connection.prepareStatement("SELECT * FROM orders");
            resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {

                Customer customer = customerDAO.getCustomerById(resultSet.getLong(2));
                Order order = new Order();
                order.setCustomer(customer);
                order.setProducts(productDAO.getByCode(resultSet.getString(3)));
                ((ArrayList) orders).add(order);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            Helper.closeStatementResultSet(statement, resultSet);
        }

        return orders;
    }

    @Override
    public void deleteOrder(Order order) {

        PreparedStatement statement = null;
        CustomerDAO customerDAO = CustomerDAOInstance.getInstance();
        long customerId = customerDAO.getId(order.getCustomer());

        for (Product product : (Collection<Product>) order.getProducts()) {

            try (Connection connection = getConnection()) {

                statement = connection.prepareStatement("DELETE FROM orders WHERE customer_id = ? AND product_code = ?");
                statement.setLong(1, customerId);
                statement.setString(2, product.getCode());
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
    public void deleteAll() {

        PreparedStatement statement = null;

        try (Connection connection = getConnection()) {

            statement = connection.prepareStatement("DELETE FROM orders ");
            statement.execute();
            connection.commit();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            Helper.closeStatementResultSet(statement, null);
        }


    }
}
