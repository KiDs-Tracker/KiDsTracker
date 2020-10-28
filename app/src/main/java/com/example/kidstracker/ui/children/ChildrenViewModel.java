package com.example.kidstracker.ui.children;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChildrenViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChildrenViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my children fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}