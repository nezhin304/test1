package com.shop.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Order {

    private Customer customer;

    private Collection products = new ArrayList<>();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection getProducts() {
        return products;
    }

    public void setProducts(Collection products) {
        this.products = products;
    }

    public void setProducts(Product product) {
        products.add(product);
    }
}
