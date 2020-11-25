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


public class RegistrationFragment extends Fragment {

    private RegistrationViewModel mViewModel;
    private FragmentRegistrationBinding mBinding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false);
        View root = mBinding.getRoot();

        onSignUpClick();
        onRegisteredAlreadyClick();

        return root;
    }

    private void onSignUpClick() {
        mBinding.bvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mBinding.etUsername.getText().toString().trim();
                String email = mBinding.etEmail.getText().toString().trim();
                String password = mBinding.etPassword.getText().toString().trim();
                String confirmedPassword = mBinding.etPasswordConfirm.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {
                    Toasty.error(getActivity(), "Please fill out all entries!", Toast.LENGTH_SHORT, true).show();

                } else if (!(username.isEmpty()) && !(email.isEmpty()) && !(password.isEmpty()) && !(confirmedPassword.isEmpty())) {

                    if (password.equals(confirmedPassword)) {
                        User user = new User(username, password, email);
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
}