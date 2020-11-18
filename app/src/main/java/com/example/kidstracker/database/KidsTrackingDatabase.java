package com.example.kidstracker.database;

import android.content.Context;
import android.content.Entity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.kidstracker.database.dao.EventDao;
import com.example.kidstracker.database.dao.NoteDao;
import com.example.kidstracker.models.Events;
import com.example.kidstracker.models.Note;

@Database(entities = {Note.class, Events.class}, version = 2)
public abstract class KidsTrackingDatabase extends RoomDatabase {

    private static KidsTrackingDatabase instance;

    public abstract NoteDao noteDao();
    public abstract EventDao eventDao();


    public static synchronized KidsTrackingDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    KidsTrackingDatabase.class, "kids_tracking_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
