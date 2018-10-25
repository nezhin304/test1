package com.shop.dao;

import com.shop.entity.Customer;
import javafx.print.Collation;

import java.util.Collection;

public interface CustomerDAO {

    void create (Customer customer);

    long getId (Customer customer);

    Collection getAll();
}
