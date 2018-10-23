package com.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Customer customer;

    private List<Product> products = new ArrayList<>();


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setProducts(Product product) {
        products.add(product);
    }
}
