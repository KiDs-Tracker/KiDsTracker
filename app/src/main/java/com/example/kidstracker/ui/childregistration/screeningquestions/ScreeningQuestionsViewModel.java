package com.example.kidstracker.ui.childregistration.screeningquestions;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.kidstracker.models.RoutineScreeningQuestion;

import java.util.List;

public class ScreeningQuestionsViewModel extends AndroidViewModel {

    private ScreeningQuestionsRepository mScreeningQuestionsRepository;
    private LiveData<List<RoutineScreeningQuestion>> mRoutineScreeningQuestionList;

    public ScreeningQuestionsViewModel(@NonNull Application application) {
        super(application);
        mScreeningQuestionsRepository = new ScreeningQuestionsRepository(application);
        mRoutineScreeningQuestionList = mScreeningQuestionsRepository.getRoutineQuestionList();
    }

    public LiveData<List<RoutineScreeningQuestion>> getRoutineQuestionList() {
        return mRoutineScreeningQuestionList;
    }
}