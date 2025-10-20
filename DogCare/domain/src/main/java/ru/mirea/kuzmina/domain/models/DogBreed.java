package ru.mirea.kuzmina.domain.models;

public class DogBreed {
    private String id;
    private String name;
    private String description;
    private String size;
    private String activityLevel;
    private String imageUrl;
    public DogBreed(String id, String name, String description, String size, String activityLevel, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.activityLevel = activityLevel;
        this.imageUrl = imageUrl;
    }
    // Геттеры
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getSize() { return size; }
    public String getActivityLevel() { return activityLevel; }
    public String getImageUrl() { return imageUrl; }
}