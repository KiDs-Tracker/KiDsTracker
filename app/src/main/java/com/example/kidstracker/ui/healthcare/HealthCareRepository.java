package com.example.kidstracker.ui.healthcare;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.ProviderDao;
import com.example.kidstracker.models.Provider;

import java.util.List;

public class HealthCareRepository {

    private ProviderDao providerDao;
    private LiveData<List<Provider>> listLiveData;

    public HealthCareRepository(Application application){
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        providerDao = kidsTrackingDatabase.providerDao();
        listLiveData = providerDao.getAllProviders();

    }

    public LiveData<List<Provider>> getAllProviders(){
        return listLiveData;
    }
}
