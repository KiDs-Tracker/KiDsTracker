package com.example.kidstracker.ui.children.editchild;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.kidstracker.models.Child;

public class EditChildViewModel extends AndroidViewModel {

    private EditChildRepository mEditChildRepository;

    public EditChildViewModel(@NonNull Application application) {
        super(application);
        mEditChildRepository = new EditChildRepository(application);
    }

    public void updateChild(Child child) {
        mEditChildRepository.updateChild(child);
    }
}
