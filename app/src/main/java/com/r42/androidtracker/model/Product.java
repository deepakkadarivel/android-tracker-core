package com.r42.androidtracker.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    private static final long serialVersionUID = 3217118697371437988L;
    private int id;
    private String name;
    private String description;
    private float price;
    private String image;
    private ArrayList<String> alternativeImages;
    private boolean available;

    public Product(int id, String name, String description, float price, String image, ArrayList<String> alternativeImages, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.alternativeImages = alternativeImages;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<String> getAlternativeImages() {
        return alternativeImages;
    }

    public boolean isAvailable() {
        return available;
    }
}
