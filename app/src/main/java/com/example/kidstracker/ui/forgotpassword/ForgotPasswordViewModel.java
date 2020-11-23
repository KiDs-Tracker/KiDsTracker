package com.example.kidstracker.ui.forgotpassword;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.kidstracker.models.User;

public class ForgotPasswordViewModel extends AndroidViewModel {

    private ForgotPasswordRepository mForgotPasswordRepository;

    public ForgotPasswordViewModel(@NonNull Application application) {
        super(application);
        mForgotPasswordRepository = new ForgotPasswordRepository(application);
    }

    public User getUser(String email) {
        return mForgotPasswordRepository.getUser(email);
    }

    public void updateUser(User user) {
        mForgotPasswordRepository.updateUser(user);
    }
}