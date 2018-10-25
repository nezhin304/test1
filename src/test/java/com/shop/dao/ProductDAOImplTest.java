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

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class ProductDAOImplTest {

    Logger logger = LoggerFactory.getLogger(ProductDAOImplTest.class);

    @Test
    public void insertAndSelectEntityTest() {

        CustomerDAO customerDAO = CustomerDAOInstance.getInstance();
        OrderDAO orderDAO = OrderDAOInstance.getInstance();
        ProductDAO productDAO = ProductDAOInstance.getInstance();

        Product product = new Product();
        product.setName("ball");
        product.setCode("0000005");
        product.setCategories("default");
        productDAO.save(product);

        Customer customer = new Customer();
        customer.setName("User");
        customerDAO.create(customer);

        Order order = new Order();
        order.setProducts(product);
        order.setCustomer(customer);
        orderDAO.create(order);

        Collection products = productDAO.getAll();

        Product productRes = ((ArrayList<Product>)products).stream()
                .filter(p -> p.getName().equals("ball"))
                .findFirst()
                .get();

        assertEquals("ball", productRes.getName());
        assertEquals("0000005", productRes.getCode());


        Collection cat = productRes.getCategories();

        assertTrue(cat.contains("default"));


        Collection customers = customerDAO.getAll();

        Customer customerRes = ((ArrayList<Customer>)customers).stream()
                .filter(c -> c.getName().equals("User"))
                .findFirst()
                .get();

        assertEquals("User", customerRes.getName());

    }
}
