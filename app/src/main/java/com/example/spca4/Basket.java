package com.example.spca4;

public class Basket {
    String category, title, manufacturer, price;

    public Basket(){

    }

    public Basket(String category, String title, String manufacturer, String price) {
        this.category = category;
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
