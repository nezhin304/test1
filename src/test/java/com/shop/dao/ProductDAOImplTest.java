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

        Product product = new Product();
        product.setName("bike");
        product.setCode("0003");

        Category category = new Category();
        category.setName("default");
        category.setProducts(product);
        product.setCategory(category);

        Customer customer = new Customer();
        customer.setName("User1");

        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(product);

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        customerDAO.create(customer);

        ProductDAOImpl productDAO = new ProductDAOImpl();
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();

        long product_id = productDAO.create(product);
        long category_id = categoryDAO.create(category);

        RelationDAOImpl relationDAO = new RelationDAOImpl();
        relationDAO.create(product_id, category_id);

        OrderDAOImpl orderDAO = new OrderDAOImpl();
        orderDAO.create(order);

    }


}
