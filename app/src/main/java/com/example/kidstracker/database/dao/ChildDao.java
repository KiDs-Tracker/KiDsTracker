package com.example.kidstracker.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kidstracker.models.Child;

import java.util.List;

@Dao
public interface ChildDao {

    @Insert
    void insertChild(Child child);

    @Query("SELECT * FROM child")
    LiveData<List<Child>> getAllChildren();
}
