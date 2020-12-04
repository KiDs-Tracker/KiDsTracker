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
import com.example.kidstracker.models.Provider;
import com.squareup.picasso.Picasso;

import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenAdapter.ChildrenViewHolder> {

    private Context mContext;
    private List<Child> mChildList;
    private ChildrenClickListener childrenClickListener;

    public interface ChildrenClickListener {
        void onChildClick(Child child);
    }

    public ChildrenAdapter(Context mContext, List<Child> mChildList, ChildrenClickListener childrenClickListener) {
        this.mContext = mContext;
        this.mChildList = mChildList;
        this.childrenClickListener = childrenClickListener;
    }

    public ChildrenAdapter() {}

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

        int age = getAge(child.getAge());
        holder.mAgeTextView.setText(String.valueOf(age));
    }

    private int getAge(String birthDate) {
        int years = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try{
            Date date1 = simpleDateFormat1.parse(birthDate);
            Date date2 = simpleDateFormat1.parse(date);

            long startDate = date1.getTime();
            long endDate = date2.getTime();

            if (startDate < endDate) {
                org.joda.time.Period period = new Period(startDate , endDate, org.joda.time.PeriodType.yearMonthDay());
                years = period.getYears();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return years;
    }

    @Override
    public int getItemCount() {
        return mChildList.size();
    }

    public Child getChildAt(int position) {
        return  mChildList.get(position);
    }

    public class ChildrenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Child child = mChildList.get(position);
            childrenClickListener.onChildClick(child);
        }
    }
}
