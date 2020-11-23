package com.example.kidstracker.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.kidstracker.models.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User WHERE userName = :userName AND password = :password")
    User getUser(String userName, String password);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUser(String email);

    @Insert
    void insert(User user);

    @Update
    void updatePassword(User user);
}
