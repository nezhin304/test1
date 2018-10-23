package com.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;

    private  String code;

    private List<Category> categories = new ArrayList<>();

    /*----------------------------------*/

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setCategories(Category category){
        categories.add(category);
    }

}
