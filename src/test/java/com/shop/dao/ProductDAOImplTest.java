package com.shop.dao;

import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.instance.CustomerDAOInstance;
import com.shop.instance.OrderDAOInstance;
import com.shop.instance.ProductDAOInstance;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class ProductDAOImplTest {

    @Test
    public void insertAndSelectEntityTest() {

        //test newbranch   dfsdfsdfsdfsdfsdfsd

        CustomerDAO customerDAO = CustomerDAOInstance.getInstance();
        OrderDAO orderDAO = OrderDAOInstance.getInstance();
        ProductDAO productDAO = ProductDAOInstance.getInstance();

        productDAO.deleteAll();
        orderDAO.deleteAll();
        customerDAO.deleteAll();

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

        Collection<Product> products = productDAO.getAll();

        Product productRes = products.stream()
                .filter(p -> p.getName().equals("ball"))
                .findFirst()
                .get();

        assertEquals("ball", productRes.getName());
        assertEquals("0000005", productRes.getCode());


        Collection<String> cat = productRes.getCategories();

        assertTrue(cat.contains("default"));


        Collection<Customer> customers = customerDAO.getAll();

        Customer customerRes = customers.stream()
                .filter(c -> c.getName().equals("User"))
                .findFirst()
                .get();

        assertEquals("User", customerRes.getName());


        Collection<Order> orders = orderDAO.getAll();

        Order orderRes = orders.stream()
                .filter(o -> o.getCustomer().getName().equals("User"))
                .findFirst()
                .get();
        assertEquals("User", orderRes.getCustomer().getName());

        Collection<Product> prodInOrder = orderRes.getProducts();

        Product prodRes = prodInOrder.stream()
                .filter(p -> p.getName().equals("ball"))
                .findFirst()
                .get();
        assertEquals("ball", prodRes.getName());
        assertEquals("0000005", prodRes.getCode());


    }
}
