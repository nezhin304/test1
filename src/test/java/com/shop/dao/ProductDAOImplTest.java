package com.shop.dao;

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

       Product product = new Product();
       product.setName("ball");
       product.setCode("0000005");
       product.setCategories("default");
       product.setCategories("outdor");

//       ProductDAOImpl productDAO = new ProductDAOImpl();
//       productDAO.save(product);

        Customer customer = new Customer();
        customer.setName("User");
//        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
//        customerDAO.create(customer);

        Order order = new Order();
        order.setProducts(product);
        order.setCustomer(customer);

        OrderDAOImpl orderDAO = new OrderDAOImpl();
        orderDAO.create(order);


    }
}
