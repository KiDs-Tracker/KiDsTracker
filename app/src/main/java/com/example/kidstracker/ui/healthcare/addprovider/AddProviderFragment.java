package com.example.kidstracker.ui.healthcare.addprovider;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.AddFragmentBinding;
import com.example.kidstracker.models.Provider;

import es.dmoral.toasty.Toasty;

public class AddProviderFragment extends Fragment {

    private AddFragmentBinding mBinding;
    private AddViewModel mViewModel;
    private Provider mProvider = new Provider();
    private Provider provider;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.add_fragment,container, false);
        View root = mBinding.getRoot();

        onGenderClick();
        onProviderTypeClick();
        onRegisterProviderClick();

        if (!(getArguments() == null)) {
            setAddEditProviders();
        }

        return root;
    }

    private void setAddEditProviders() {
        provider = (Provider) getArguments().getSerializable("provider");
        mBinding.etFacilityLocation.setText(provider.getLocation());
        mBinding.etPatientName.setText(provider.getPatientName());
        mBinding.etProviderName.setText(provider.getName());
        mBinding.etProviderNumber.setText(provider.getNumber());

        String occupation = "";
        switch(provider.getPhysician()){
            case 0:
                occupation = "Cardiologist";
                mBinding.tvProvider.setText(occupation);
                break;
            case 1:
                occupation = "Neurologist";
                mBinding.tvProvider.setText(occupation);
                break;
            case 2:
                occupation = "Dentist";
                mBinding.tvProvider.setText(occupation);
                break;
            case 3:
                occupation = "Optometrist";
                mBinding.tvProvider.setText(occupation);
                break;
            case 4:
                occupation = "Ophthalmologist";
                mBinding.tvProvider.setText(occupation);
                break;
            case 5:
                occupation = "Otolaryngologist";
                mBinding.tvProvider.setText(occupation);
                break;
            case 6:
                occupation = "Pulmonologist";
                mBinding.tvProvider.setText(occupation);
                break;
            case 7:
                occupation = "Surgeon";
                mBinding.tvProvider.setText(occupation);
                break;
            case 8:
                occupation = "Primary Care Doctor";
                mBinding.tvProvider.setText(occupation);
                break;
            case 9:
                occupation = "Orthopedist";
                mBinding.tvProvider.setText(occupation);
                break;
            case 10:
                occupation = "Gastroenterologist";
                mBinding.tvProvider.setText(occupation);
                break;
        }

        String gender = "";
        switch(provider.getGender()){
            case 0:
                gender = "Male";
                mBinding.tvGender.setText(gender);
                break;
            case 1:
                gender = "Female";
                mBinding.tvGender.setText(gender);
                break;
        }
    }

    private void onRegisterProviderClick() {
        mBinding.btRegisterProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String providerName = mBinding.etProviderName.getText().toString().trim();
                String patientName = mBinding.etPatientName.getText().toString().trim();
                String number = mBinding.etProviderNumber.getText().toString().trim();
                String location = mBinding.etFacilityLocation.getText().toString().trim();

                if(providerName.isEmpty() || patientName.isEmpty() || number.isEmpty() || location.isEmpty()){
                    Toasty.error(getActivity(),"Please fill out all entries!", Toast.LENGTH_SHORT, true).show();
                }else {

                    if (!(getArguments() == null)) {
                        Provider updatedProvider = new Provider();
                        updatedProvider.setId(provider.getId());
                        updatedProvider.setGender(provider.getGender());
                        updatedProvider.setPhysician(provider.getPhysician());
                        updatedProvider.setLocation(location);
                        updatedProvider.setName(providerName);
                        updatedProvider.setNumber(number);
                        updatedProvider.setPatientName(patientName);
                        mViewModel.updateProvider(updatedProvider);
                    } else {
                        mProvider.setName(providerName);
                        mProvider.setPatientName(patientName);
                        mProvider.setNumber(number);
                        mProvider.setLocation(location);
                        mViewModel.insertProvider(mProvider);
                    }

                    goToHealthCare();
                }

            }
        });
    }

    private void goToHealthCare() {
        Navigation.findNavController(getView()).navigate(R.id.nav_health_care);
    }

    private void onProviderTypeClick() {
        mBinding.ibProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose a doctor")
                        .setItems(R.array.providers_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String physician = "";
                                switch(which){
                                    case 0:
                                        physician = "Cardiologist";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(0);
                                        } else {
                                            mProvider.setPhysician(0);
                                        }
                                        break;
                                    case 1:
                                        physician = "Neurologist";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(1);
                                        } else {
                                            mProvider.setPhysician(1);
                                        }
                                        break;
                                    case 2:
                                        physician = "Dentist";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(2);
                                        } else {
                                            mProvider.setPhysician(2);
                                        }
                                        break;
                                    case 3:
                                        physician = "Optometrist";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(3);
                                        } else {
                                            mProvider.setPhysician(3);
                                        }
                                        break;
                                    case 4:
                                        physician = "Ophthalmologist";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(4);
                                        } else {
                                            mProvider.setPhysician(4);
                                        }
                                        break;
                                    case 5:
                                        physician = "Otolaryngologist";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(5);
                                        } else {
                                            mProvider.setPhysician(5);
                                        }
                                        break;
                                    case 6:
                                        physician = "Pulmonologist";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(6);
                                        } else {
                                            mProvider.setPhysician(6);
                                        }
                                        break;
                                    case 7:
                                        physician = "Surgeon";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(7);
                                        } else {
                                            mProvider.setPhysician(7);
                                        }
                                        break;
                                    case 8:
                                        physician = "Primary Care Doctor";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(8);
                                        } else {
                                            mProvider.setPhysician(8);
                                        }
                                        break;
                                    case 9:
                                        physician = "Orthopedist";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(9);
                                        } else {
                                            mProvider.setPhysician(9);
                                        }
                                        break;
                                    case 10:
                                        physician = "Gastroenterologist";
                                        mBinding.tvProvider.setText(physician);
                                        if (!(getArguments() == null)) {
                                            provider.setPhysician(10);
                                        } else {
                                            mProvider.setPhysician(10);
                                        }
                                        break;



                                }
                            }
                        });
                builder.show();
            }
        });
    }


    private void onGenderClick() {
        mBinding.ibGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pick a gender")
                        .setItems(R.array.gender_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String gender = "";
                                switch (which){
                                    case 0:
                                        gender = "Male";
                                        mBinding.tvGender.setText(gender);
                                        if (!(getArguments() == null)) {
                                            provider.setGender(0);
                                        } else {
                                            mProvider.setGender(0);
                                        }
                                        break;

                                    case 1:
                                        gender = "Female";
                                        mBinding.tvGender.setText(gender);
                                        if (!(getArguments() == null)) {
                                            provider.setGender(1);
                                        } else {
                                            mProvider.setGender(1);
                                        }
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddViewModel.class);
    }

}