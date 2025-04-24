package com.example.Stoneware;

public class BannerItem {
    private final int imageResId;
    private final String text;

    public BannerItem(int imageResId, String text) {
        this.imageResId = imageResId;
        this.text = text;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getText() {
        return text;
    }
}