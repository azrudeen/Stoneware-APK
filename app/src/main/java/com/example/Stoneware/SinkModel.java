package com.example.Stoneware;

public class SinkModel {
    private String name;
    private int imageResId;
    private String price;
    private boolean isFavorite;

    public SinkModel(String name, int imageResId, String price) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.isFavorite = false; // default state
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getPrice() {
        return price;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}