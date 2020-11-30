package com.example.kidstracker.ui.healthcare.providerinfo;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.ProviderInfoFragmentBinding;
import com.example.kidstracker.models.Provider;
import com.squareup.picasso.Picasso;

public class ProviderInfoFragment extends Fragment {

    private ProviderInfoViewModel mViewModel;
    private ProviderInfoFragmentBinding mBinding;
    private Provider mProvider;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.provider_info_fragment, container, false);
        View root = mBinding.getRoot();

        mProvider = (Provider) getArguments().getSerializable("provider");

        setProviderInfo();

        mBinding.fbEditProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("provider", mProvider);
                Navigation.findNavController(getView()).navigate(R.id.nav_add_provider, bundle);
            }
        });

        return root;
    }

    private void setProviderInfo() {
        mBinding.tvPhysicianName.setText(mProvider.getName());
        mBinding.tvPhysicianLocation.setText(mProvider.getLocation());
        mBinding.tvPhysicianNumber.setText(mProvider.getNumber());
        mBinding.tvPatientName.setText(mProvider.getPatientName());

        String occupation = "";
        switch(mProvider.getPhysician()){
            case 0:
                occupation = "Cardiologist";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 1:
                occupation = "Neurologist";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 2:
                occupation = "Dentist";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 3:
                occupation = "Optometrist";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 4:
                occupation = "Ophthalmologist";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 5:
                occupation = "Otolaryngologist";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 6:
                occupation = "Pulmonologist";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 7:
                occupation = "Surgeon";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 8:
                occupation = "Primary Care Doctor";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 9:
                occupation = "Orthopedist";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
            case 10:
                occupation = "Gastroenterologist";
                mBinding.tvPhysicianOccupation.setText(occupation);
                break;
        }

        String gender = "";
        switch(mProvider.getGender()){
            case 0:
                gender = "Male";
                mBinding.tvPhysicianGender.setText(gender);
                Picasso.get().load(R.drawable.male_doctor1).into(mBinding.ivGender);
                break;
            case 1:
                gender = "Female";
                mBinding.tvPhysicianGender.setText(gender);
                Picasso.get().load(R.drawable.female_doctor).into(mBinding.ivGender);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProviderInfoViewModel.class);
    }

}