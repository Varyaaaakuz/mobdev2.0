package ru.mirea.kuzmina.fragmentmanagerapp;

public class Country {
    private String name;
    private String capital;
    private long population;

    private String description;

    public Country(String name, String capital, long population, String description) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.description = description;
    }

    public String getName() { return name; }
    public String getCapital() { return capital; }
    public long getPopulation() { return population; }
    public String getDescription() { return description; }
}