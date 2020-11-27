package com.example.kidstracker.ui.childregistration.medicalquestions;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.kidstracker.models.Child;
import com.example.kidstracker.models.MedicalQuestion;

import java.util.List;

public class MedicalQuestionsViewModel extends AndroidViewModel {

    private MedicalQuestionsRepository mMedicalQuestionsRepository;
    private LiveData<List<MedicalQuestion>> mListLiveData;

    public MedicalQuestionsViewModel(@NonNull Application application) {
        super(application);
        mMedicalQuestionsRepository = new MedicalQuestionsRepository(application);
        mListLiveData = mMedicalQuestionsRepository.getMedicalQuestionList();
    }

    public LiveData<List<MedicalQuestion>> getMedicalQuestionList() {
        return mListLiveData;
    }

    public void insertChild(Child child) {
        mMedicalQuestionsRepository.insertChild(child);
    }
}