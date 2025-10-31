package ru.mirea.kuzmina.domain.models;

public class Dog {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String size;
    private String activityLevel;

    public Dog(String id, String name, String description, String imageUrl, String size, String activityLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.size = size;
        this.activityLevel = activityLevel;
    }

    // Геттеры
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public String getSize() { return size; }
    public String getActivityLevel() { return activityLevel; }
}