package com.example.kidstracker.ui.healthcare.addprovider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.kidstracker.models.Provider;

public class AddViewModel extends AndroidViewModel {

    private AddProviderRepository addProviderRepository;

    public AddViewModel(@NonNull Application application) {
        super(application);

        addProviderRepository = new AddProviderRepository(application);

    }

    public void insertProvider(Provider provider){
        addProviderRepository.insertProvider(provider);
    }
}
