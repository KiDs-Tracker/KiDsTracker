package com.example.kidstracker.ui.healthcare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.FragmentHealthCareBinding;

public class HealthCareFragment extends Fragment {

    private FragmentHealthCareBinding mBinding;
    private HealthCareViewModel mHealthCareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_health_care, container, false);
        View root = mBinding.getRoot();

        mBinding.btAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddProvider();
            }
        });

        return root;
    }

    private void goToAddProvider() {
        Navigation.findNavController(getView()).navigate(R.id.nav_add_provider);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHealthCareViewModel = new ViewModelProvider(this).get(HealthCareViewModel.class);
    }
}