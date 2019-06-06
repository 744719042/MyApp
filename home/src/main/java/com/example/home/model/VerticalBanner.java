package com.example.home.model;

public class VerticalBanner {
    private int id;
    private String url;
    private String name;
    private String desc;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getClickThrough() {
        return clickThrough;
    }

    public void setClickThrough(String clickThrough) {
        this.clickThrough = clickThrough;
    }
}
