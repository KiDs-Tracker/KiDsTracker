package com.example.kidstracker.ui.forgotpassword;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kidstracker.R;
import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.databinding.ForgotPasswordFragmentBinding;
import com.example.kidstracker.models.User;
import com.example.kidstracker.ui.login.LoginFragment;

import es.dmoral.toasty.Toasty;

public class ForgotPasswordFragment extends Fragment {

    private ForgotPasswordViewModel mViewModel;
    private ForgotPasswordFragmentBinding mBinding;
    private User mUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.forgot_password_fragment, container, false);
        View root = mBinding.getRoot();

        onSearchClick();
        onChangePassword();
        onRememberedPasswordClick();

        return root;
    }

    private void onRememberedPasswordClick() {
        mBinding.tvRememberedPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                LoginFragment fragment = new LoginFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void onChangePassword() {
        mBinding.btUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = mBinding.etPassword.getText().toString().trim();
                String confirmedPassword = mBinding.etPasswordConfirm.getText().toString().trim();

                if (password.equals(confirmedPassword) && (!password.isEmpty() && !confirmedPassword.isEmpty())) {
                    mUser.setPassword(password);
                    AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mViewModel.updateUser(mUser);

                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toasty.success(getActivity(), "Password has changed!", Toast.LENGTH_SHORT, true).show();
                                }
                            });

                            FragmentManager fragmentManager = getParentFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                            LoginFragment fragment = new LoginFragment();
                            fragmentTransaction.replace(R.id.fragment_container, fragment);
                            fragmentTransaction.commit();
                        }
                    });

                } else if (password.isEmpty() || confirmedPassword.isEmpty()) {
                    Toasty.error(getActivity(), "Please input password", Toast.LENGTH_SHORT, true).show();

                } else {
                    Toasty.error(getActivity(), "Passwords don't match", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    private void onSearchClick() {
        mBinding.btSearchEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mBinding.etEmail.getText().toString().trim();

                AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        mUser = mViewModel.getUser(email);
                        Handler handler = new Handler(Looper.getMainLooper());
                        if (mUser != null) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mBinding.vfFlipper.showNext();
                                }
                            });
                        } else {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toasty.error(getActivity(), "That email does not exist!", Toast.LENGTH_SHORT, true).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
    }

}