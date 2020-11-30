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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.kidstracker.R;
import com.example.kidstracker.adapters.ProviderAdapter;
import com.example.kidstracker.databinding.FragmentHealthCareBinding;
import com.example.kidstracker.models.Provider;

import java.util.List;

public class HealthCareFragment extends Fragment {

    private FragmentHealthCareBinding mBinding;
    private HealthCareViewModel mHealthCareViewModel;
    private ProviderAdapter providerAdapter = new ProviderAdapter();

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
        mHealthCareViewModel.getAllProviders().observe(getViewLifecycleOwner(), new Observer<List<Provider>>() {
            @Override
            public void onChanged(List<Provider> providers) {
                if(providers != null){
                    createAdapter(providers);

                }else{
                    mBinding.tvNoProviders.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void createAdapter(List<Provider> providers) {
        mBinding.rvProvider.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvProvider.setHasFixedSize(true);
        providerAdapter = new ProviderAdapter(getContext(),providers);
        mBinding.rvProvider.setAdapter(providerAdapter);


    }
}