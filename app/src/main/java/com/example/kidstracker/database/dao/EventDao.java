package com.example.kidstracker.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kidstracker.models.Events;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insertEvent(Events events);

    @Delete
    void deleteEvent(Events events);

    @Query("SELECT * FROM events WHERE month = :month AND year = :year")
    List<Events> getEvents(String month, String year);

    @Query("SELECT * FROM events WHERE date = :date")
    List<Events> getEventsOnDate(String date);
}
