package com.example.kidstracker.ui.registration;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.kidstracker.models.User;

public class RegistrationViewModel extends AndroidViewModel {

    private RegistrationRepository mRepository;

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
        mRepository = new RegistrationRepository(application);
    }

    public void insertUser(User user) {
        mRepository.insertUser(user);
    }
}
