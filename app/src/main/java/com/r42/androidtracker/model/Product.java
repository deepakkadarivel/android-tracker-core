package com.r42.androidtracker.model;

import java.util.ArrayList;

public class Product {
    private String name;
    private String description;
    private float price;
    private String image;
    private ArrayList<String> alternativeImages;
    private boolean available;

    public Product(String name, String description, float price, String image, ArrayList<String> alternativeImages, boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.alternativeImages = alternativeImages;
        this.available = available;
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

    public static ArrayList<Product> createProductList() {
        ArrayList<String> alternativeImagesList = new ArrayList<String>();
        alternativeImagesList.add("https://source.unsplash.com/random/800x600");


        ArrayList<Product> products = new ArrayList<Product>();
        for (int i = 1; i <= 10; i++) {
            products.add(new Product(
                    "Product " + i,
                    "This is product " + i,
                    100 * i,
                    "https://source.unsplash.com/random/800x600",
                    alternativeImagesList,
                    i % 3 != 0));
        }

        return products;
    }
}
