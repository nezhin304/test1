package com.shop.instance;

import com.shop.dao.OrderDAO;
import com.shop.dao.OrderDAOImpl;

public class OrderDAOInstance {

    public static class Holder {

        static final OrderDAO ORDER_INSTANCE = new OrderDAOImpl();
    }

    public static OrderDAO getInstance() {

        return Holder.ORDER_INSTANCE;
    }
}
