package com.example.itsmiddleme.model;

public class OrderModel {
    
    String orderpoint;
    boolean option;

    public OrderModel() {
    }

    public OrderModel(String orderpoint, boolean option) {
        this.orderpoint = orderpoint;
        this.option = option;
    }

    public String getOrderpoint() {
        return orderpoint;
    }

    public void setOrderpoint(String orderpoint) {
        this.orderpoint = orderpoint;
    }

    public boolean isOption() {
        return option;
    }

    public void setOption(boolean option) {
        this.option = option;
    }
}
