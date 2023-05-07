package com.example.mobilalk;

public class CompareItem {
    private String name;
    private String info;
    private float ratedInfo;
    private  int imageResource;
    private String id;
    public CompareItem(String name, String info,  float ratedInfo, int imageResource) {
        this.name = name;
        this.info = info;
        this.ratedInfo = ratedInfo;
        this.imageResource = imageResource;
    }
    public CompareItem() {
    }

    public String getName() {
        return name;
    }
    public String getInfo() {
        return info;
    }
    public float getRatedInfo() {
        return ratedInfo;
    }
    public int getImageResource() {
        return imageResource;
    }

    public String _getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
