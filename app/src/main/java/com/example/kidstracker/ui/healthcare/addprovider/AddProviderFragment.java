package com.example.kidstracker.ui.healthcare.addprovider;

import androidx.annotation.NavigationRes;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.renderscript.ScriptGroup;
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



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.add_fragment,container, false);
        View root = mBinding.getRoot();

        onGenderClick();
        onProviderTypeClick();
        onRegisterProviderClick();


        return root;
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
                    mProvider.setName(providerName);
                    mProvider.setPatientName(patientName);
                    mProvider.setNumber(number);
                    mProvider.setLocation(location);
                    mViewModel.insertProvider(mProvider);

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
                                        mProvider.setPhysician(0);
                                        break;
                                    case 1:
                                        physician = "Neurologist";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(1);
                                        break;
                                    case 2:
                                        physician = "Dentist";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(2);
                                        break;
                                    case 3:
                                        physician = "Optometrist";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(3);
                                        break;
                                    case 4:
                                        physician = "Ophthalmologist";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(4);
                                        break;
                                    case 5:
                                        physician = "Otolaryngologist";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(5);
                                        break;
                                    case 6:
                                        physician = "Pulmonologist";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(6);
                                        break;
                                    case 7:
                                        physician = "Surgeon";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(7);
                                        break;
                                    case 8:
                                        physician = "Primary Care Doctor";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(8);
                                        break;
                                    case 9:
                                        physician = "Orthopedist";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(9);
                                        break;
                                    case 10:
                                        physician = "Gastroenterologist";
                                        mBinding.tvProvider.setText(physician);
                                        mProvider.setPhysician(10);
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
                                        mProvider.setGender(0);
                                        break;

                                    case 1:
                                        gender = "Female";
                                        mBinding.tvGender.setText(gender);
                                        mProvider.setGender(1);
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