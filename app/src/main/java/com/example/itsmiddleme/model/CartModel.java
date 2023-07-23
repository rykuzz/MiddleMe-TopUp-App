package com.example.itsmiddleme.model;

public class CartModel {

    String current_date, current_time, name_product, price_product, total_quantity;
    int total_price;

    public CartModel(String current_date, String current_time, String name_product, String price_product, String total_quantity, int total_price) {
        this.current_date = current_date;
        this.current_time = current_time;
        this.name_product = name_product;
        this.price_product = price_product;
        this.total_quantity = total_quantity;
        this.total_price = total_price;

    }

    public CartModel() {
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPrice_product() {
        return price_product;
    }

    public void setPrice_product(String price_product) {
        this.price_product = price_product;
    }

    public String getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}