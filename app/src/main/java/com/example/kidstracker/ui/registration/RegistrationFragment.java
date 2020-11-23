package com.example.kidstracker.ui.registration;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.FragmentRegistrationBinding;
import com.example.kidstracker.models.User;
import com.example.kidstracker.ui.login.LoginFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class RegistrationFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private RegistrationViewModel mViewModel;
    private FragmentRegistrationBinding mBinding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false);
        View root = mBinding.getRoot();

        onDatePickerClick();
        onSignUpClick();
        onRegisteredAlreadyClick();
        onGenderClick();

        return root;
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

    private void onSignUpClick() {
        mBinding.bvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mBinding.etUsername.getText().toString().trim();
                String email = mBinding.etEmail.getText().toString().trim();
                String password = mBinding.etPassword.getText().toString().trim();
                String confirmedPassword = mBinding.etPasswordConfirm.getText().toString().trim();
                String dateOfBirth = mBinding.tvDate.getText().toString().trim();
                String gender = mBinding.tvGender.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty() || dateOfBirth.isEmpty() || gender.isEmpty()) {
                    Toasty.error(getActivity(), "Please fill out all entries!", Toast.LENGTH_SHORT, true).show();

                } else if (!(username.isEmpty()) && !(email.isEmpty()) && !(password.isEmpty()) && !(confirmedPassword.isEmpty()) && !(dateOfBirth.isEmpty()) && !(gender.isEmpty())) {

                    if (password.equals(confirmedPassword)) {
                        int num = 0;
                        if(gender.equals("Male")) {
                            num = 0;
                        } else if (gender.equals("Female")) {
                            num = 1;
                        }

                        User user = new User(username, password, email, dateOfBirth, num);
                        mViewModel.insertUser(user);
                        goToLogin();
                    } else {
                        Toasty.error(getActivity(), "Passwords don't match", Toast.LENGTH_SHORT, true).show();
                    }
                }
            }
        });
    }

    private void onRegisteredAlreadyClick() {
        mBinding.tvAlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToLogin();
            }
        });
    }

    private void goToLogin(){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        LoginFragment fragment = new LoginFragment();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void onDatePickerClick() {
        mBinding.ibDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        RegistrationFragment.this,
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
        mBinding.tvDate.setText(date);
    }
}