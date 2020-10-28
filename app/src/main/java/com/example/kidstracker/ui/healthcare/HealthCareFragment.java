package com.example.kidstracker.ui.healthcare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.kidstracker.R;

public class HealthCareFragment extends Fragment {

    private HealthCareViewModel mHealthCareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mHealthCareViewModel =
                new ViewModelProvider(this).get(HealthCareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_health_care, container, false);
        final TextView textView = root.findViewById(R.id.text_health_care);
        mHealthCareViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}