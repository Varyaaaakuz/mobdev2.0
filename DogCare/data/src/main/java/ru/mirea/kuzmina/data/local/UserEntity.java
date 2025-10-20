package ru.mirea.kuzmina.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey
    @NonNull
    public String id;
    public String email;
    public String name;

    public UserEntity(@NonNull String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}