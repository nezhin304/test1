package com.shop.dao;

import com.shop.entity.Customer;

import java.util.Collection;

public interface CustomerDAO {

    void create (Customer customer);

    long getId (Customer customer);

    void deleteAll();

    Customer getCustomerById(long id);

    Collection<Customer> getAll();
}
