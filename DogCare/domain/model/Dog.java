package ru.mirea.kuzmina.dogcare.domain.models;

public class Dog {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private String careAdvice;

    public Dog(int id, String name, String description, String imageUrl, String careAdvice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.careAdvice = careAdvice;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public String getCareAdvice() { return careAdvice; }
}