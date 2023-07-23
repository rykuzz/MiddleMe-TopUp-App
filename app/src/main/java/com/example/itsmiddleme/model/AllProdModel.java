package com.example.itsmiddleme.model;

import java.io.Serializable;

public class AllProdModel implements Serializable {

    String desc,name,rating,img_url,type;
    int price;

    public AllProdModel(String desc, String name, String rating, String img_url, String type, int price) {
        this.desc = desc;
        this.name = name;
        this.rating = rating;
        this.img_url = img_url;
        this.type = type;
        this.price = price;
    }

    public AllProdModel() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
