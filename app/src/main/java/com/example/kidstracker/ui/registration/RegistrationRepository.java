package com.example.kidstracker.ui.registration;

import android.app.Application;

import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.UserDao;
import com.example.kidstracker.models.User;

public class RegistrationRepository {

    private UserDao mUserDao;

    public RegistrationRepository(Application application) {
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        mUserDao = kidsTrackingDatabase.userDao();
    }

    public void insertUser(User user) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.insert(user);
            }
        });
    }
}
