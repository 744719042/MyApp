package com.example.home.model;

public class Card {
    private int id;
    private String url;
    private String title;
    private String subTitle;
    private String clickThrough;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getClickThrough() {
        return clickThrough;
    }

    public void setClickThrough(String clickThrough) {
        this.clickThrough = clickThrough;
    }
}
