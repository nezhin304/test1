package com.shop.entity;

import java.util.List;

public class Category {

    private String name;

    private List<Product> products;

    /*-------------------------------*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
