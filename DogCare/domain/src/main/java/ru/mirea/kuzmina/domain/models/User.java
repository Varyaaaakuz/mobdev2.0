package ru.mirea.kuzmina.domain.models;

public class User {
    private final String id;
    private final String email;
    private final String name;

    public User(String id, String email, String name) {
        if (id == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }
        this.id = id;
        this.email = email != null ? email : "";
        this.name = name;
    }

    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}