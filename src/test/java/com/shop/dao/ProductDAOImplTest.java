package com.shop.dao;

import com.shop.entity.Category;
import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.Product;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDAOImplTest {

    Logger logger = LoggerFactory.getLogger(ProductDAOImplTest.class);

    @Test
    public void create() {

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        ProductDAOImpl productDAO = new ProductDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();

        Product product = new Product();
        product.setName("bike");
        product.setCode("00003");

        Category category = new Category();
        category.setName("default");
        product.setCategories(category);

        productDAO.create(product);

        Product product1 = new Product();
        product1.setName("ball");
        product1.setCode("00005");

        Category category1 = new Category();
        category1.setName("outdor");
        product1.setCategories(category1);

        productDAO.create(product1);

        Customer customer = new Customer();
        customer.setName("User");

        customerDAO.create(customer);

        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(product);
        order.setProducts(product1);

        orderDAO.create(order);

    }
}
