package com.example.kidstracker.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kidstracker.models.MedicalQuestion;

import java.util.List;

@Dao
public interface MedicalQuestionDao {

    @Insert
    void insertMedicalQuestion(MedicalQuestion medicalQuestion);

    @Query("SELECT * FROM medicalquestion")
    LiveData<List<MedicalQuestion>> getListOfMedicalQuestions();
}
