package com.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;

    private String code;

    private List<String> categories = new ArrayList<>();

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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setCategories(String category) {
        categories.add(category);
    }

}
