package com.example.kidstracker.adapters;

import android.content.Context;
import android.icu.util.LocaleData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidstracker.R;
import com.example.kidstracker.models.Child;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenAdapter.ChildrenViewHolder> {

    private Context mContext;
    private List<Child> mChildList;

    public ChildrenAdapter(Context mContext, List<Child> mChildList) {
        this.mContext = mContext;
        this.mChildList = mChildList;
    }

    @NonNull
    @Override
    public ChildrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
        return new ChildrenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildrenViewHolder holder, int position) {
        Child child = mChildList.get(position);
        String name = child.getFirstName() + " " + child.getLastName();
        holder.mNameTextView.setText(name);
        holder.mAgeTextView.setText(String.valueOf(child.getAge()));
        switch (child.getDiagnosis()) {
            case 0:
                holder.mDiagnosisTextView.setText("Down Syndrome");
        }

        switch (child.getGender()) {
            case 0:
                Picasso.get().load(R.drawable.ic_male).into(holder.mGenderImageView);
                break;
            case 1:
                Picasso.get().load(R.drawable.ic_female).into(holder.mGenderImageView);
        }

    }

    @Override
    public int getItemCount() {
        return mChildList.size();
    }

    public class ChildrenViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private TextView mAgeTextView;
        private TextView mDiagnosisTextView;
        private ImageView mGenderImageView;

        public ChildrenViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTextView = itemView.findViewById(R.id.tv_name);
            mAgeTextView = itemView.findViewById(R.id.tv_age);
            mDiagnosisTextView = itemView.findViewById(R.id.tv_diagnosis);
            mGenderImageView = itemView.findViewById(R.id.iv_gender);
        }
    }
}
