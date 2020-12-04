package com.example.kidstracker.ui.children.editchild;

import android.app.Application;

import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.ChildDao;
import com.example.kidstracker.models.Child;

public class EditChildRepository {

    private ChildDao mChildDao;

    public EditChildRepository(Application application) {
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        mChildDao = kidsTrackingDatabase.childDao();
    }

    public void updateChild(Child child) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mChildDao.updateChild(child);
            }
        });
    }
}
