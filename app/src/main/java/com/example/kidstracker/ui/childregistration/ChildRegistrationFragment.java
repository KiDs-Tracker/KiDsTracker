package com.example.kidstracker.ui.childregistration;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.ChildRegistrationFragmentBinding;
import com.example.kidstracker.ui.registration.RegistrationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class ChildRegistrationFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private ChildRegistrationViewModel mViewModel;
    private ChildRegistrationFragmentBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.child_registration_fragment, container, false);
        View root = mBinding.getRoot();

        onDatePickerClick();
        onGenderClick();
        onDiagnosisClick();
        onRegisterChildClick();

        return root;
    }

    private void onDiagnosisClick() {
        mBinding.ibDiagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pick a diagnosis")
                        .setItems(R.array.diagnosis_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String diagnosis = "";
                                switch (which) {
                                    case 0:
                                        diagnosis = "Down Syndrome";
                                        mBinding.tvDiagnosis.setText(diagnosis);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });
    }

    private void onRegisterChildClick() {
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
                                switch (which) {
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
                        });
                builder.show();
            }
        });
    }

    private void onDatePickerClick() {
        mBinding.ibDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        ChildRegistrationFragment.this,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChildRegistrationViewModel.class);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int Month = month + 1;
        String date = Month + "/" + dayOfMonth + "/" + year;
        mBinding.tvDate.setText(date);
    }
}