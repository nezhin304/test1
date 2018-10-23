package com.shop.dao;

import com.shop.entity.Category;
import com.shop.entity.Customer;
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
        category.setProduct(product);
        product.setCategory(category);

        ProductDAOImpl productDAO = new ProductDAOImpl();
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();

        long product_id = productDAO.create(product);
        long category_id = categoryDAO.create(category);

        RelationDAOImpl relationDAO = new RelationDAOImpl();
        relationDAO.create(product_id, category_id);






//        Customer customer = new Customer();
//        customer.setName("User1");
//
//        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
//
//        customerDAO.create(customer);

    }


}
