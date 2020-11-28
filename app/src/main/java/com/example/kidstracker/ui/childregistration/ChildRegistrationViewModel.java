package com.example.kidstracker.ui.childregistration;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ChildRegistrationViewModel extends AndroidViewModel {

    private ChildRegistrationRepository mChildRegistrationRepository;

    public ChildRegistrationViewModel(@NonNull Application application) {
        super(application);
        mChildRegistrationRepository = new ChildRegistrationRepository(application);
    }

    public int getAge(String birthDate) {
        return mChildRegistrationRepository.getAge(birthDate);
    }
}
