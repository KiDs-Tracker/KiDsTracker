package com.example.kidstracker.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.kidstracker.models.User;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository mLoginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mLoginRepository = new LoginRepository(application);
    }

    public User getUser(String username, String password) {
        return mLoginRepository.getUser(username, password);
    }
}