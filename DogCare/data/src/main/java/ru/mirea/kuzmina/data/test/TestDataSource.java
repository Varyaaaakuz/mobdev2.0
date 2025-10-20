package ru.mirea.kuzmina.data.test;

import ru.mirea.kuzmina.domain.models.Dog;
import java.util.Arrays;
import java.util.List;

public class TestDataSource {

    public List<Dog> getTestDogs() {
        return Arrays.asList(
                new Dog("1", "Buddy", "Labrador Retriever", ""),
                new Dog("2", "Max", "German Shepherd", ""),
                new Dog("3", "Charlie", "Golden Retriever", "")
        );
    }
}