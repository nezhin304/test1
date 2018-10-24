package com.shop.dao;

import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.instance.CustomerDAOInstance;
import com.shop.instance.OrderDAOInstance;
import com.shop.instance.ProductDAOInstance;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDAOImplTest {

    Logger logger = LoggerFactory.getLogger(ProductDAOImplTest.class);

    @Test
    public void create() {

        CustomerDAOImpl customerDAO = CustomerDAOInstance.getInstance();
        OrderDAOImpl orderDAO = OrderDAOInstance.getInstance();
        ProductDAOImpl productDAO = ProductDAOInstance.getInstance();

        Product product = new Product();
        product.setName("ball");
        product.setCode("0000005");
        product.setCategories("default");
        product.setCategories("outdor");
        productDAO.save(product);

        Customer customer = new Customer();
        customer.setName("User");
        customerDAO.create(customer);

        Order order = new Order();
        order.setProducts(product);
        order.setCustomer(customer);
        orderDAO.create(order);


    }
}
