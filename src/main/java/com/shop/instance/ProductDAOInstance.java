package com.shop.instance;

import com.shop.dao.OrderDAOImpl;
import com.shop.dao.ProductDAOImpl;

public class ProductDAOInstance {

    public static class Holder {

        static final ProductDAOImpl PRODUCT_INSTANCE = new ProductDAOImpl();
    }

    public static ProductDAOImpl getInstance() {

        return Holder.PRODUCT_INSTANCE;
    }
}
