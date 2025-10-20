package ru.mirea.kuzmina.domain.models;

public class Dog {
    private String id;
    private String name;
    private String breed;
    private String imageUrl;

    public Dog(String id, String name, String breed, String imageUrl) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.imageUrl = imageUrl;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getBreed() { return breed; }
    public String getImageUrl() { return imageUrl; }
}