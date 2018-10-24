package com.shop.instance;

import com.shop.dao.CustomerDAOImpl;

public class CustomerDAOInstance {

    public static class Holder {

        static final CustomerDAOImpl CUSTOMER_INSTANCE = new CustomerDAOImpl();
    }

    public static CustomerDAOImpl getInstance() {

        return Holder.CUSTOMER_INSTANCE;
    }

}
