package ru.mirea.kuzmina.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.OnConflictStrategy;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

    @Query("SELECT * FROM users WHERE id = :userId")
    UserEntity getUser(String userId);

    @Query("DELETE FROM users")
    void clearUsers();
}