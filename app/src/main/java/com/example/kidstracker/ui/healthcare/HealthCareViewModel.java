package com.example.kidstracker.ui.healthcare;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kidstracker.models.Provider;

import java.util.List;

public class HealthCareViewModel extends AndroidViewModel {

    private HealthCareRepository healthCareRepository;
    private LiveData<List<Provider>> listLiveData;

    public HealthCareViewModel(@NonNull Application application) {
        super(application);
        healthCareRepository = new HealthCareRepository(application);
        listLiveData = healthCareRepository.getAllProviders();

    }
    public LiveData<List<Provider>> getAllProviders(){
        return listLiveData;
    }
}