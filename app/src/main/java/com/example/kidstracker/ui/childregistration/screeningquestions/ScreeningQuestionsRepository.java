package com.example.kidstracker.ui.childregistration.screeningquestions;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.RoutineScreeningQuestionDao;
import com.example.kidstracker.models.RoutineScreeningQuestion;

import java.util.List;

public class ScreeningQuestionsRepository {

    private RoutineScreeningQuestionDao mRoutineScreeningQuestionDao;
    private LiveData<List<RoutineScreeningQuestion>> mRoutineScreeningQuestionList;

    public ScreeningQuestionsRepository(Application application) {
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        mRoutineScreeningQuestionDao = kidsTrackingDatabase.routineScreeningQuestionDao();
        mRoutineScreeningQuestionList = mRoutineScreeningQuestionDao.getListOfRoutineScreeningQuestions();
    }

    public LiveData<List<RoutineScreeningQuestion>> getRoutineQuestionList() {
        return mRoutineScreeningQuestionList;
    }
}
