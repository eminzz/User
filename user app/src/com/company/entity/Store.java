package com.company.entity;

import java.io.Serializable;

public class Store implements Serializable {
    private User[] list;

    public User[] getList() {
        return list;
    }

    public void setList(User[] list) {
        this.list = list;
    }
}
