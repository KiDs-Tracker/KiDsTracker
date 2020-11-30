package com.example.kidstracker.ui.healthcare.addprovider;

import android.app.Application;

import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.ProviderDao;
import com.example.kidstracker.models.Provider;

public class AddProviderRepository {

    private ProviderDao providerDao;

    public AddProviderRepository(Application application){
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        providerDao = kidsTrackingDatabase.providerDao();

    }

    public void insertProvider(Provider provider){
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                providerDao.insertProvider(provider);
            }
        });
    }
}
