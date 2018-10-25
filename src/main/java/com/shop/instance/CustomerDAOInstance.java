package com.shop.instance;

import com.shop.dao.CustomerDAO;
import com.shop.dao.CustomerDAOImpl;

public class CustomerDAOInstance {

    public static class Holder {

        static final CustomerDAO CUSTOMER_INSTANCE = new CustomerDAOImpl();
    }

    public static CustomerDAO getInstance() {

        return Holder.CUSTOMER_INSTANCE;
    }

}
