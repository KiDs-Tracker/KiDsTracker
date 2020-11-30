package com.example.kidstracker.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kidstracker.models.Provider;

import java.util.List;

@Dao
public interface ProviderDao {

    @Insert
    void insertProvider(Provider provider);
    @Delete
    void deleteProvider(Provider provider);
    @Query("SELECT * FROM provider")
    LiveData<List<Provider>> getAllProviders();
    @Query("DELETE FROM provider")
    void deleteAllProviders();

}
