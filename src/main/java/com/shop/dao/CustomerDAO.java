package com.shop.dao;

import com.shop.entity.Customer;

public interface CustomerDAO {

    void create (Customer customer);

    long getId (Customer customer);
}
