package com.example.kidstracker.ui.login;

import androidx.annotation.UiThread;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kidstracker.HomeActivity;
import com.example.kidstracker.R;
import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.databinding.LoginFragmentBinding;
import com.example.kidstracker.models.User;
import com.example.kidstracker.ui.forgotpassword.ForgotPasswordFragment;
import com.example.kidstracker.ui.notes.NotesViewModel;
import com.example.kidstracker.ui.registration.RegistrationFragment;
import com.example.kidstracker.ui.registration.RegistrationViewModel;

import es.dmoral.toasty.Toasty;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private LoginFragmentBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        View root = mBinding.getRoot();

        checkCheckBoxState();

        mBinding.bvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterFragment();
            }
        });

        mBinding.bvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomeActivity();
            }
        });

        mBinding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToForgotPasswordFragment();
            }
        });

        return root;
    }

    private void checkCheckBoxState() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean checkboxState = sharedPreferences.getBoolean("checkbox", false);
        if (checkboxState) {
            startActivity(new Intent(getActivity(), HomeActivity.class));
        }
    }

    private void goToForgotPasswordFragment() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    private void goToRegisterFragment() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RegistrationFragment fragment = new RegistrationFragment();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void goToHomeActivity() {
        String username = mBinding.evUsername.getText().toString().trim();
        String password = mBinding.evPassword.getText().toString().trim();

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                User user = mViewModel.getUser(username, password);
                if (user != null) {
                    rememberLoginState(username);
                    Intent homeIntent = new Intent(getContext(), HomeActivity.class);
                    startActivity(homeIntent);
                } else {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toasty.error(getActivity(), "Username or password is not recognizable!", Toast.LENGTH_SHORT, true).show();
                        }
                    });
                }
            }
        });
    }

    private void rememberLoginState(String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userName);
        if (mBinding.cbRemember.isChecked()) {
            editor.putBoolean("checkbox", true);
        }
        editor.apply();
    }

}