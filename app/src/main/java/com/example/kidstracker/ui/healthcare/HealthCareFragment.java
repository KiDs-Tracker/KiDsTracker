package com.example.kidstracker.ui.healthcare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidstracker.R;
import com.example.kidstracker.adapters.ProviderAdapter;
import com.example.kidstracker.databinding.FragmentHealthCareBinding;
import com.example.kidstracker.models.Provider;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class HealthCareFragment extends Fragment {

    private FragmentHealthCareBinding mBinding;
    private HealthCareViewModel mHealthCareViewModel;
    private ProviderAdapter providerAdapter = new ProviderAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_health_care, container, false);
        View root = mBinding.getRoot();

        setHasOptionsMenu(true);

        mBinding.btAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddProvider();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mHealthCareViewModel.deleteProvider(providerAdapter.getProviderAt(viewHolder.getAdapterPosition()));
                Toasty.success(getContext(), "Provider deleted", Toast.LENGTH_SHORT, true).show();
            }
        }).attachToRecyclerView(mBinding.rvProvider);

        return root;
    }

    private void goToAddProvider() {
        Navigation.findNavController(getView()).navigate(R.id.nav_add_provider);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHealthCareViewModel = new ViewModelProvider(this).get(HealthCareViewModel.class);
        mHealthCareViewModel.getAllProviders().observe(getViewLifecycleOwner(), new Observer<List<Provider>>() {
            @Override
            public void onChanged(List<Provider> providers) {
                if(providers != null){
                    createAdapter(providers);
                }else{
                    mBinding.tvNoProviders.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void createAdapter(List<Provider> providers) {
        mBinding.rvProvider.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvProvider.setHasFixedSize(true);
        providerAdapter = new ProviderAdapter(getContext(), providers, new ProviderAdapter.ProviderClickListener() {
            @Override
            public void onProviderClick(Provider provider) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("provider", provider);
                Navigation.findNavController(getView()).navigate(R.id.nav_provider_info, bundle);
            }
        });
        mBinding.rvProvider.setAdapter(providerAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.provider_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_providers:
                mHealthCareViewModel.deleteAllProviders();
                Toasty.success(getContext(), "All children deleted", Toast.LENGTH_SHORT, true).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}