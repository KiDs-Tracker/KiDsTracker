package com.example.kidstracker.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.kidstracker.models.RoutineScreeningQuestion;

import java.util.List;

@Dao
public interface RoutineScreeningQuestionDao {

    @Insert
    void insertRoutineScreeningQuestion(RoutineScreeningQuestion routineScreeningQuestion);

    @Query("SELECT * FROM routinescreeningquestion")
    LiveData<List<RoutineScreeningQuestion>> getListOfRoutineScreeningQuestions();
}
