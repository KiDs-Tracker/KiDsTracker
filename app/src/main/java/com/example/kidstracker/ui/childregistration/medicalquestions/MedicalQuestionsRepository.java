package com.example.kidstracker.ui.childregistration.medicalquestions;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.ChildDao;
import com.example.kidstracker.database.dao.MedicalQuestionDao;
import com.example.kidstracker.models.Child;
import com.example.kidstracker.models.MedicalQuestion;

import java.util.List;

public class MedicalQuestionsRepository {

    private MedicalQuestionDao mMedicalQuestionDao;
    private ChildDao mChildDao;
    private LiveData<List<MedicalQuestion>> mListLiveData;

    public MedicalQuestionsRepository(Application application) {
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        mMedicalQuestionDao = kidsTrackingDatabase.medicalQuestionDao();
        mChildDao = kidsTrackingDatabase.childDao();
        mListLiveData = mMedicalQuestionDao.getListOfMedicalQuestions();
    }

    public LiveData<List<MedicalQuestion>> getMedicalQuestionList() {
        return mListLiveData;
    }

    public void insertChild(Child child) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mChildDao.insertChild(child);
            }
        });
    }
}
