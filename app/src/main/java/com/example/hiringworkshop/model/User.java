package com.example.hiringworkshop.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}