package com.shop.instance;

import com.shop.dao.ProductDAO;
import com.shop.dao.ProductDAOImpl;

public class ProductDAOInstance {

    public static class Holder {

        static final ProductDAO PRODUCT_INSTANCE = new ProductDAOImpl();
    }

    public static ProductDAO getInstance() {

        return Holder.PRODUCT_INSTANCE;
    }
}
