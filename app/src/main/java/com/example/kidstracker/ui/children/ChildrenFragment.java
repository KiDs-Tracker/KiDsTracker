package com.example.kidstracker.ui.children;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidstracker.R;
import com.example.kidstracker.adapters.ChildrenAdapter;
import com.example.kidstracker.databinding.FragmentChildrenBinding;
import com.example.kidstracker.models.Child;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class ChildrenFragment extends Fragment {

    private ChildrenViewModel mViewModel;
    private FragmentChildrenBinding mBinding;
    private ChildrenAdapter mChildrenAdapter = new ChildrenAdapter();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_children, container, false);
        View root = mBinding.getRoot();

        setHasOptionsMenu(true);

        onAddChildButtonClick();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.deleteChild(mChildrenAdapter.getChildAt(viewHolder.getAdapterPosition()));
                Toasty.success(getContext(), "Child deleted", Toast.LENGTH_SHORT, true).show();
            }
        }).attachToRecyclerView(mBinding.rvChildren);

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
        mChildrenAdapter = new ChildrenAdapter(getContext(), children);
        mBinding.rvChildren.setAdapter(mChildrenAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.children_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_children:
                mViewModel.deleteAllChildren();
                Toasty.success(getContext(), "All children deleted", Toast.LENGTH_SHORT, true).show();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}