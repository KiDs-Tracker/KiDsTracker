package com.example.kidstracker.ui.forgotpassword;

import android.app.Application;

import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.UserDao;
import com.example.kidstracker.models.User;

public class ForgotPasswordRepository {

    private UserDao mUserDao;

    public ForgotPasswordRepository(Application application) {
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        mUserDao = kidsTrackingDatabase.userDao();
    }

    public User getUser(String email) {
        return mUserDao.getUser(email);
    }

    public void updateUser(User user) {
        mUserDao.updatePassword(user);
    }
}
