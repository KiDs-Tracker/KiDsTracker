package com.example.kidstracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidstracker.R;
import com.example.kidstracker.models.Provider;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder> {

    private Context context;
    private List<Provider> providerList;
    private ProviderClickListener providerClickListener;

    public interface ProviderClickListener {
        void onProviderClick(Provider provider);
    }

    public ProviderAdapter(){

    }

    public ProviderAdapter(Context context, List<Provider> providerList, ProviderClickListener providerClickListener) {
        this.context = context;
        this.providerList = providerList;
        this.providerClickListener = providerClickListener;
    }

    @NonNull
    @Override
    public ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_item, parent, false);
        return new ProviderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHolder holder, int position) {
        Provider provider = providerList.get(position);
        holder.physicianNameTextView.setText(provider.getName());
        holder.patientNameTextView.setText(provider.getPatientName());
        String occupation = "";
        switch(provider.getPhysician()){
            case 0:
                occupation = "Cardiologist";
                holder.occupationTextView.setText(occupation);
                break;
            case 1:
                occupation = "Neurologist";
                holder.occupationTextView.setText(occupation);
                break;
            case 2:
                occupation = "Dentist";
                holder.occupationTextView.setText(occupation);
                break;
            case 3:
                occupation = "Optometrist";
                holder.occupationTextView.setText(occupation);
                break;
            case 4:
                occupation = "Ophthalmologist";
                holder.occupationTextView.setText(occupation);
                break;
            case 5:
                occupation = "Otolaryngologist";
                holder.occupationTextView.setText(occupation);
                break;
            case 6:
                occupation = "Pulmonologist";
                holder.occupationTextView.setText(occupation);
                break;
            case 7:
                occupation = "Surgeon";
                holder.occupationTextView.setText(occupation);
                break;
            case 8:
                occupation = "Primary Care Doctor";
                holder.occupationTextView.setText(occupation);
                break;
            case 9:
                occupation = "Orthopedist";
                holder.occupationTextView.setText(occupation);
                break;
            case 10:
                occupation = "Gastroenterologist";
                holder.occupationTextView.setText(occupation);
                break;

        }
        switch(provider.getGender()){
            case 0:
                Picasso.get().load(R.drawable.male_doctor1).into(holder.imageView);
                break;
            case 1:
                Picasso.get().load(R.drawable.female_doctor).into(holder.imageView);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return providerList.size();
    }

    public Provider getProviderAt(int position) {
        return providerList.get(position);
    }

    public class ProviderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView physicianNameTextView;
        private TextView occupationTextView;
        private TextView patientNameTextView;

        public ProviderViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_gender);
            physicianNameTextView = itemView.findViewById(R.id.tv_physician_name);
            occupationTextView = itemView.findViewById(R.id.tv_physician_occupation);
            patientNameTextView = itemView.findViewById(R.id.tv_patient);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Provider provider = providerList.get(position);
            providerClickListener.onProviderClick(provider);
        }
    }
}
