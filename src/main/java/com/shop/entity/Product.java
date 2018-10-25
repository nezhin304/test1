package com.shop.entity;

import java.util.ArrayList;
import java.util.Collection;

public class Product {

    private String name;

    private String code;

    private Collection categories = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection getCategories() {
        return categories;
    }

    public void setCategories(Collection categories) {
        this.categories = categories;
    }

    public void setCategories(String category) {
        categories.add(category);
    }

}
