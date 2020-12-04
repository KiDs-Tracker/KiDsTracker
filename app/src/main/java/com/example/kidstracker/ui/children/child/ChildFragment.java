package com.example.kidstracker.ui.children.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.ChildFragmentBinding;
import com.example.kidstracker.models.Child;

import org.joda.time.Period;
import org.joda.time.Years;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class ChildFragment extends Fragment {

    private ChildFragmentBinding mBinding;
    private Child mChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.child_fragment, container, false);
        View root = mBinding.getRoot();

        mChild = (Child) getArguments().getSerializable("child");

        setChildBasicInfo();
        setAnswers();

        mBinding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNextQuestions();
            }
        });

        mBinding.btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreviousQuestions();
            }
        });

        mBinding.fbEditChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditPage();
            }
        });

        return root;
    }

    private void goToEditPage() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("my_child", mChild);
        Navigation.findNavController(getView()).navigate(R.id.nav_edit_child, bundle);
    }

    private void setChildBasicInfo() {
        String name = mChild.getFirstName() + " " + mChild.getLastName();
        mBinding.tvName.setText(name);

        int age = setChildAge(mChild.getAge());
        mBinding.tvAge.setText(String.valueOf(age));

        String gender = "";
        switch (mChild.getGender()) {
            case 0:
                gender = "Male";
                mBinding.tvGender.setText(gender);
                mBinding.ivGender.setImageResource(R.drawable.ic_male);
                break;
            case 1:
                gender = "Female";
                mBinding.tvGender.setText(gender);
                mBinding.ivGender.setImageResource(R.drawable.ic_female);
                break;
        }
    }

    private int setChildAge(String birthDate) {
        int years = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try{
            Date date1 = simpleDateFormat1.parse(birthDate);
            Date date2 = simpleDateFormat1.parse(date);

            assert date1 != null;
            long startDate = date1.getTime();
            assert date2 != null;
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


    private void setAnswers() {
        setScreeningAnswers();
        setMedicalAnswers();
    }

    private void setMedicalAnswers() {
        String choice = "";
        switch (mChild.getPriorSurgeries()) {
            case 0:
                choice = "Yes";
                mBinding.tvPriorSurgeriesAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvPriorSurgeriesAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvPriorSurgeriesAnswer.setText(choice);
                mBinding.tvMoreInfo1.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorEyeProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvEyeAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvEyeAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvEyeAnswer.setText(choice);
                mBinding.tvMoreInfo2.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorHearingProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvHearingAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvHearingAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvHearingAnswer.setText(choice);
                mBinding.tvMoreInfo3.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorNeckProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvNeckAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvNeckAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvNeckAnswer.setText(choice);
                mBinding.tvMoreInfo4.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorThyroidProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvThyroidAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvThyroidAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvThyroidAnswer.setText(choice);
                mBinding.tvMoreInfo5.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorHeartProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvHeartAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvHeartAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvHeartAnswer.setText(choice);
                mBinding.tvMoreInfo6.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorBreathingProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvBreathingAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvBreathingAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvBreathingAnswer.setText(choice);
                mBinding.tvMoreInfo7.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorConstipationProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvConstipationAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvConstipationAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvConstipationAnswer.setText(choice);
                mBinding.tvMoreInfo8.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorSleepingProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvSleepAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvSleepAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvSleepAnswer.setText(choice);
                mBinding.tvMoreInfo9.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorBrainProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvBrainAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvBrainAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvBrainAnswer.setText(choice);
                mBinding.tvMoreInfo10.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorBloodProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvBloodAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvBloodAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvBloodAnswer.setText(choice);
                mBinding.tvMoreInfo11.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        switch (mChild.getPriorImmuneProblems()) {
            case 0:
                choice = "Yes";
                mBinding.tvImmuneAnswer.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvImmuneAnswer.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvImmuneAnswer.setText(choice);
                mBinding.tvMoreInfo12.setVisibility(View.VISIBLE);
                mBinding.tvMoreInfo12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openUpAlertDialogue();
                    }
                });
                break;
        }

        mBinding.tvMedicationAnswer.setText(mChild.getMedications());
    }

    private void openUpAlertDialogue() {
        new AlertDialog.Builder(getContext())
                .setTitle("Information")
                .setMessage("Would you like to know more about this section?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toasty.info(getActivity(), "Functionality does't do anything yet", Toast.LENGTH_SHORT, true).show();
                    }
                }).create().show();
    }

    private void setScreeningAnswers() {
        if (mChild.getBaseWCC().isEmpty()) {
            mBinding.tvLastWcDate.setText("No set date");
        } else {
            mBinding.tvLastWcDate.setText(mChild.getBaseWCC());
        }

        if (!mChild.getBaseNextWCC().isEmpty()) {
            mBinding.tvNextWcDate.setText(mChild.getBaseNextWCC());
        } else {
            mBinding.tvNextWcDate.setText("DUE NOW");
        }

        if (mChild.getBaseHearing().isEmpty()) {
            mBinding.tvLastHearingDate.setText("No set date");
        } else {
            mBinding.tvLastHearingDate.setText(mChild.getBaseHearing());
        }

        if (!mChild.getBaseNextHearing().isEmpty()) {
            mBinding.tvNextHearingDate.setText(mChild.getBaseNextHearing());
        } else {
            mBinding.tvNextHearingDate.setText("DUE NOW");
        }

        if (mChild.getBaseVision().isEmpty()) {
            mBinding.tvLastEyeDate.setText("No set date");
        } else {
            mBinding.tvLastEyeDate.setText(mChild.getBaseVision());
        }

        if (mChild.getBaseHGB().isEmpty()) {
            mBinding.tvLastBloodIronDate.setText("No set date");
        } else {
            mBinding.tvLastBloodIronDate.setText(mChild.getBaseHGB());
        }

        if (mChild.getBaseTSH().isEmpty()) {
            mBinding.tvLastBloodThyroidDate.setText("No set date");
        } else {
            mBinding.tvLastBloodThyroidDate.setText(mChild.getBaseTSH());
        }

        String choice = "";
        switch (mChild.getBaseHearingResult()) {
            case 0:
                choice = "Yes";
                mBinding.tvHearingTestResult.setText(choice);
                break;
            case 1:
                choice = "No";
                mBinding.tvHearingTestResult.setText(choice);
                break;
            case 2:
                choice = "Don't Know";
                mBinding.tvHearingTestResult.setText(choice);
                break;
        }

        setNextBaseWCC(mChild.getBaseWCC());
        setNextHearingResult(mChild.getBaseHearingResult());
        setNextEyeExam(mChild.getBaseVision());
        setNextBloodIron(mChild.getBaseHGB());
        setNextBloodThyroid(mChild.getBaseTSH());
    }

    private void setNextBloodThyroid(String baseTSH) {
        String date = mChild.getBaseTSH();
        if (baseTSH.isEmpty()) {
            mBinding.tvBbThyroidNext.setText("DUE NOW");
        } else {
            Calendar calendar = getNewBBloodThyroidDate(date);
            int days = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String newDate = month + "/" + days + "/" + year;
            mBinding.tvBbThyroidNext.setText(newDate);
        }
    }

    private Calendar getNewBBloodThyroidDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try {
            Date dateObject = dateFormat.parse(date);
            assert dateObject != null;
            calendar.setTime(dateObject);
            calendar.add(Calendar.MONTH, 13);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    private void setNextBloodIron(String baseHGB) {
        String date = mChild.getBaseHGB();
        if (baseHGB.isEmpty()) {
            mBinding.tvBbIronNext.setText("DUE NOW");
        } else {
            Calendar calendar = getNewBBloodIronDate(date);
            int days = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String newDate = month + "/" + days + "/" + year;
            mBinding.tvBbIronNext.setText(newDate);
        }
    }

    private Calendar getNewBBloodIronDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try {
            Date dateObject = dateFormat.parse(date);
            assert dateObject != null;
            calendar.setTime(dateObject);
            calendar.add(Calendar.MONTH, 13);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    private void setNextEyeExam(String baseVision) {
        String date = mChild.getBaseVision();
        if (baseVision.isEmpty()) {
            mBinding.tvEeNext.setText("DUE NOW");
        } else {
            Calendar calendar = getNewBVDate(date);
            int days = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String newDate = month + "/" + days + "/" + year;
            mBinding.tvEeNext.setText(newDate);
        }
    }

    private Calendar getNewBVDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try {
            Date dateObject = dateFormat.parse(date);
            assert dateObject != null;
            calendar.setTime(dateObject);
            calendar.add(Calendar.MONTH, 13);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    private void setNextHearingResult(int baseHearingResult) {
        String date = mChild.getBaseHearing();
        if (date.isEmpty()) {
            mBinding.tvHhNext.setText("DUE NOW");
        } else {
            Calendar calendar = getNewBHDate(date, baseHearingResult);
            int days = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String newDate = month + "/" + days + "/" + year;
            mBinding.tvHhNext.setText(newDate);
            if (mChild.getBaseNextHearing().isEmpty()) {
                mBinding.tvNextHearingDate.setText(newDate);
            }
        }
    }

    private Calendar getNewBHDate(String date, int baseHearingResult) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try {
            Date dateObject = dateFormat.parse(date);
            assert dateObject != null;
            calendar.setTime(dateObject);

            if (baseHearingResult == 0) {
                calendar.add(Calendar.MONTH, 13);
            } else if (baseHearingResult == 1) {
                calendar.add(Calendar.MONTH, 7);
            } else {
                calendar.add(Calendar.MONTH, 7);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    private void setNextBaseWCC(String baseWCC) {
        String dateOfBirth = mChild.getAge();
        if (baseWCC.isEmpty()) {
            mBinding.tvCcNext.setText("DUE NOW");
        } else {
            int months = getAge(dateOfBirth);
            Calendar calendar = getNewWCCDate(baseWCC, months);
            int days = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            String date = month + "/" + days + "/" + year;
            mBinding.tvCcNext.setText(date);
            if (mChild.getBaseNextWCC().isEmpty()) {
                mBinding.tvNextWcDate.setText(date);
            }
        }
    }

    private int getAge(String dateOfBirth) {
        int years = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try{
            Date date1 = simpleDateFormat1.parse(dateOfBirth);
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

         int months = years * 12;

        return months;
    }

    private Calendar getNewWCCDate(String dateOfBirth, int months) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try {
            Date dateObject = dateFormat.parse(dateOfBirth);
            assert dateObject != null;
            calendar.setTime(dateObject);
            if (12 <= months &&  months <= 17) {
                calendar.add(Calendar.MONTH, 4);
            } else if (18 <= months && months <= 35) {
                calendar.add(Calendar.MONTH, 7);
            } else if (36 <= months) {
                calendar.add(Calendar.MONTH, 13);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    private void setPreviousQuestions() {
        mBinding.vfChangeQuestions.showPrevious();

        mBinding.scrollView.fullScroll(View.FOCUS_UP);

        mBinding.tvPriorSurgeriesAnswer.setVisibility(View.GONE);
        mBinding.tvEyeAnswer.setVisibility(View.GONE);
        mBinding.tvHearingAnswer.setVisibility(View.GONE);
        mBinding.tvNeckAnswer.setVisibility(View.GONE);
        mBinding.tvThyroidAnswer.setVisibility(View.GONE);
        mBinding.tvHeartAnswer.setVisibility(View.GONE);
        mBinding.tvBreathingAnswer.setVisibility(View.GONE);
        mBinding.tvConstipationAnswer.setVisibility(View.GONE);
        mBinding.tvBrainAnswer.setVisibility(View.GONE);
        mBinding.tvBloodAnswer.setVisibility(View.GONE);
        mBinding.tvImmuneAnswer.setVisibility(View.GONE);
        mBinding.tvMedicationAnswer.setVisibility(View.GONE);

        mBinding.view1.setVisibility(View.GONE);
        mBinding.view2.setVisibility(View.GONE);
        mBinding.view3.setVisibility(View.GONE);
        mBinding.view4.setVisibility(View.GONE);
        mBinding.view5.setVisibility(View.GONE);
        mBinding.view6.setVisibility(View.GONE);
        mBinding.view7.setVisibility(View.GONE);
        mBinding.view8.setVisibility(View.GONE);
        mBinding.view9.setVisibility(View.GONE);
        mBinding.view10.setVisibility(View.GONE);
        mBinding.view11.setVisibility(View.GONE);
        mBinding.view12.setVisibility(View.GONE);

        mBinding.tvPrompt1.setVisibility(View.GONE);
        mBinding.tvPrompt2.setVisibility(View.GONE);
        mBinding.tvPrompt3.setVisibility(View.GONE);
        mBinding.tvPrompt4.setVisibility(View.GONE);
        mBinding.tvPrompt5.setVisibility(View.GONE);
        mBinding.tvPrompt6.setVisibility(View.GONE);
        mBinding.tvPrompt7.setVisibility(View.GONE);
        mBinding.tvPrompt8.setVisibility(View.GONE);
        mBinding.tvPrompt9.setVisibility(View.GONE);
        mBinding.tvPrompt10.setVisibility(View.GONE);
        mBinding.tvPrompt11.setVisibility(View.GONE);
        mBinding.tvPrompt12.setVisibility(View.GONE);
    }

    private void setNextQuestions() {
        mBinding.vfChangeQuestions.showNext();
        mBinding.scrollView.fullScroll(View.FOCUS_UP);
        setVisibilityForAnswers();
    }

    private void setVisibilityForAnswers() {
        mBinding.tvPriorSurgeriesAnswer.setVisibility(View.VISIBLE);
        mBinding.tvEyeAnswer.setVisibility(View.VISIBLE);
        mBinding.tvHearingAnswer.setVisibility(View.VISIBLE);
        mBinding.tvNeckAnswer.setVisibility(View.VISIBLE);
        mBinding.tvThyroidAnswer.setVisibility(View.VISIBLE);
        mBinding.tvHeartAnswer.setVisibility(View.VISIBLE);
        mBinding.tvBreathingAnswer.setVisibility(View.VISIBLE);
        mBinding.tvConstipationAnswer.setVisibility(View.VISIBLE);
        mBinding.tvSleepAnswer.setVisibility(View.VISIBLE);
        mBinding.tvBrainAnswer.setVisibility(View.VISIBLE);
        mBinding.tvBloodAnswer.setVisibility(View.VISIBLE);
        mBinding.tvImmuneAnswer.setVisibility(View.VISIBLE);
        mBinding.tvMedicationAnswer.setVisibility(View.VISIBLE);

        mBinding.view1.setVisibility(View.VISIBLE);
        mBinding.view2.setVisibility(View.VISIBLE);
        mBinding.view3.setVisibility(View.VISIBLE);
        mBinding.view4.setVisibility(View.VISIBLE);
        mBinding.view5.setVisibility(View.VISIBLE);
        mBinding.view6.setVisibility(View.VISIBLE);
        mBinding.view7.setVisibility(View.VISIBLE);
        mBinding.view8.setVisibility(View.VISIBLE);
        mBinding.view9.setVisibility(View.VISIBLE);
        mBinding.view10.setVisibility(View.VISIBLE);
        mBinding.view11.setVisibility(View.VISIBLE);
        mBinding.view12.setVisibility(View.VISIBLE);

        mBinding.tvPrompt1.setVisibility(View.VISIBLE);
        mBinding.tvPrompt2.setVisibility(View.VISIBLE);
        mBinding.tvPrompt3.setVisibility(View.VISIBLE);
        mBinding.tvPrompt4.setVisibility(View.VISIBLE);
        mBinding.tvPrompt5.setVisibility(View.VISIBLE);
        mBinding.tvPrompt6.setVisibility(View.VISIBLE);
        mBinding.tvPrompt7.setVisibility(View.VISIBLE);
        mBinding.tvPrompt8.setVisibility(View.VISIBLE);
        mBinding.tvPrompt9.setVisibility(View.VISIBLE);
        mBinding.tvPrompt10.setVisibility(View.VISIBLE);
        mBinding.tvPrompt11.setVisibility(View.VISIBLE);
        mBinding.tvPrompt12.setVisibility(View.VISIBLE);
    }
}