package com.example.kidstracker.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.kidstracker.models.Child;
import com.example.kidstracker.models.Provider;

import java.util.List;

@Dao
public interface ChildDao {

    @Insert
    void insertChild(Child child);

    @Query("SELECT * FROM child")
    LiveData<List<Child>> getAllChildren();

    @Query("DELETE FROM child")
    void deleteAllChildren();

    @Delete
    void deleteChild(Child child);

    @Update
    void updateChild(Child child);
}
