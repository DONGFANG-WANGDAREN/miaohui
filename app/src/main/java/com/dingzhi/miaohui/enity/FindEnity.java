package com.dingzhi.miaohui.enity;

/**
 * Created by Shall on 2015-06-23.
 */
public class FindEnity {
    private String name;
    private int year;
    private String images;

    public FindEnity(String name, int year, String images) {
        this.name = name;
        this.year = year;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }
}
