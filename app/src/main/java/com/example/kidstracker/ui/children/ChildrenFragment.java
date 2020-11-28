package com.example.kidstracker.ui.children;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kidstracker.R;
import com.example.kidstracker.adapters.ChildrenAdapter;
import com.example.kidstracker.databinding.FragmentChildrenBinding;
import com.example.kidstracker.models.Child;

import java.util.List;

public class ChildrenFragment extends Fragment {

    private ChildrenViewModel mViewModel;
    private FragmentChildrenBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_children, container, false);
        View root = mBinding.getRoot();

        onAddChildButtonClick();

        return root;
    }

    private void onAddChildButtonClick() {
        mBinding.btAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_child_registration);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChildrenViewModel.class);
        mViewModel.getAllChildren().observe(getViewLifecycleOwner(), new Observer<List<Child>>() {
            @Override
            public void onChanged(List<Child> children) {
                if (children != null) {
                    createAdapter(children);
                } else {
                    mBinding.tvNoChildren.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void createAdapter(List<Child> children) {
        mBinding.rvChildren.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvChildren.setHasFixedSize(true);
        ChildrenAdapter childrenAdapter = new ChildrenAdapter(getContext(), children);
        mBinding.rvChildren.setAdapter(childrenAdapter);
    }
}