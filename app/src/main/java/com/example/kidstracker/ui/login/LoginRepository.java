package com.example.kidstracker.ui.login;

import android.app.Application;

import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.UserDao;
import com.example.kidstracker.models.User;

public class LoginRepository {

    private UserDao mUserDao;

    public LoginRepository(Application application) {
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        mUserDao = kidsTrackingDatabase.userDao();
    }

    public User getUser(String username, String password) {
        return mUserDao.getUser(username, password);
    }
}
