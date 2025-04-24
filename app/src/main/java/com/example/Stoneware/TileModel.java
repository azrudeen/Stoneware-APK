package com.example.Stoneware;

public class TileModel {
    private String name;
    private String price;
    private int imageResId;

    public TileModel(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}