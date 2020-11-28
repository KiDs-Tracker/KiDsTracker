package com.example.kidstracker.ui.childregistration;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
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
import android.widget.Toast;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.ChildRegistrationFragmentBinding;
import com.example.kidstracker.models.Child;
import com.example.kidstracker.models.User;
import com.example.kidstracker.ui.registration.RegistrationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class ChildRegistrationFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private ChildRegistrationFragmentBinding mBinding;
    private ChildRegistrationViewModel mViewModel;
    private Child mChild = new Child();

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChildRegistrationViewModel.class);
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
                                        mChild.setDiagnosis(0);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });
    }

    private void onRegisterChildClick() {
        mBinding.btRegisterChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNameToChild();
                if (!(mChild.getAge() < 0) && (mChild.getGender() == 0 || mChild.getGender() == 1) && mChild.getDiagnosis() == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("child", mChild);
                    Navigation.findNavController(v).navigate(R.id.nav_child_screening, bundle);
                } else {
                    Toasty.error(getActivity(), "Please fill out all entries!", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    private void insertNameToChild() {
        String firstName = mBinding.etFirstName.getText().toString().trim();
        String lastName = mBinding.etLastName.getText().toString().trim();
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            mChild.setFirstName(firstName);
            mChild.setLastName(lastName);
        } else {
            Toasty.error(getActivity(), "Please fill out all entries!", Toast.LENGTH_SHORT, true).show();
        }
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
                                        mChild.setGender(0);
                                        break;
                                    case 1:
                                        gender = "Female";
                                        mBinding.tvGender.setText(gender);
                                        mChild.setGender(1);
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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int Month = month + 1;
        String date = Month + "/" + dayOfMonth + "/" + year;
        String birthDate = dayOfMonth + "/" + Month + "/" + year;
        mBinding.tvDate.setText(date);
        calculateBirthDay(birthDate);
    }

    private void calculateBirthDay(String birthDate) {
        int age = mViewModel.getAge(birthDate);
        mChild.setAge(age);
    }
}