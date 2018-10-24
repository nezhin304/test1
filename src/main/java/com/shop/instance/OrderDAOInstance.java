package com.shop.instance;

import com.shop.dao.OrderDAOImpl;

public class OrderDAOInstance {

    public static class Holder {

        static final OrderDAOImpl ORDER_INSTANCE = new OrderDAOImpl();
    }

    public static OrderDAOImpl getInstance() {

        return Holder.ORDER_INSTANCE;
    }
}
