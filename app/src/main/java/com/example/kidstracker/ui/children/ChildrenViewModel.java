package com.example.kidstracker.ui.children;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kidstracker.models.Child;

import java.util.List;

public class ChildrenViewModel extends AndroidViewModel {

    private ChildrenRepository mChildrenRepository;
    private LiveData<List<Child>> mListLiveData;

    public ChildrenViewModel(@NonNull Application application) {
        super(application);
        mChildrenRepository = new ChildrenRepository(application);
        mListLiveData = mChildrenRepository.getAllChildren();
    }

    public LiveData<List<Child>> getAllChildren() {
        return mListLiveData;
    }

    public void deleteAllChildren() {
        mChildrenRepository.deleteAllChildren();
    }

    public void deleteChild(Child child) {
        mChildrenRepository.deleteChild(child);
    }
}