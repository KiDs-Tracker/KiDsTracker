package com.example.kidstracker.ui.children;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.ChildDao;
import com.example.kidstracker.models.Child;

import java.util.List;

public class ChildrenRepository {

    private ChildDao mChildDao;
    private LiveData<List<Child>> mListLiveData;

    public ChildrenRepository(Application application) {
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        mChildDao = kidsTrackingDatabase.childDao();
        mListLiveData = mChildDao.getAllChildren();
    }

    public LiveData<List<Child>> getAllChildren() {
        return  mListLiveData;
    }
}
