package com.shop.dao;

import com.shop.entity.Category;
import com.shop.entity.Product;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

public class ProductDAOImplTest {

    Logger logger = LoggerFactory.getLogger(ProductDAOImplTest.class);

    @Test
    public void create() {

        Product product = new Product();
        product.setName("ball");
        product.setCode("0001");

        Category category = new Category();
        category.setName("default");
        category.setProduct(product);
        product.setCategory(category);

        Category category1 = new Category();
        category1.setName("sport");
        category1.setProduct(product);
        product.setCategory(category1);

//        ProductDAOImpl productDAO = new ProductDAOImpl();
//        productDAO.create(product);
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        categoryDAO.create(category);

        logger.info(product.getName());
        logger.info(product.getCode());

        List<Category> categories = product.getCategories();

        for (Category cat: categories) {

            logger.info(cat.getName());
        }


    }


}