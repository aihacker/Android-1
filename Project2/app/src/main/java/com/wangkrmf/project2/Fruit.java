package com.wangkrmf.project2;

public class Fruit {
    private int imageID;
    private  String name;
    private  String price;


    public Fruit(int imageID, String name, String price) {
        this.imageID = imageID;
        this.name = name;
        this.price = price;
    }

    public int getImageID() {
        return imageID;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
