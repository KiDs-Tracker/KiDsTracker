package com.example.kidstracker.ui.childregistration.screeningquestions;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.kidstracker.R;
import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.databinding.ScreeningQuestionsFragmentBinding;
import com.example.kidstracker.models.Child;
import com.example.kidstracker.models.RoutineScreeningQuestion;
import com.example.kidstracker.ui.childregistration.ChildRegistrationFragment;

import java.util.Calendar;
import java.util.List;
import java.util.Observable;

import es.dmoral.toasty.Toasty;

public class ScreeningQuestionsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = ScreeningQuestionsFragment.class.getSimpleName();

    private ScreeningQuestionsViewModel mViewModel;
    private ScreeningQuestionsFragmentBinding mBinding;

    private Child mChild;
    private List<RoutineScreeningQuestion> mRoutineScreeningQuestionList;

    private int questionCounter;
    private int questionTotal;
    private RoutineScreeningQuestion currentQuestion;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.screening_questions_fragment, container, false);
        View root = mBinding.getRoot();

        mChild = (Child) getArguments().getSerializable("child");
        onCalendarClick();
        onNextQuestionClick();
        goToMedicalQuestionsFragment();

        return root;
    }

    private void onCalendarClick() {
        mBinding.ibDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        ScreeningQuestionsFragment.this,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
    }

    private void onNextQuestionClick() {
        mBinding.bvNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = mBinding.tvDate.getText().toString().trim();
                if (!answer.isEmpty() || (mBinding.rbOptionOne.isChecked() || mBinding.rbOptionTwo.isChecked() || mBinding.rbOptionThree.isChecked())) {
                    switch(questionCounter) {
                        case 0:
                            mChild.setBaseWCC(mBinding.tvDate.getText().toString().trim());
                            mBinding.tvDate.setText("");
                            break;
                        case 1:
                            mChild.setBaseNextWCC(mBinding.tvDate.getText().toString().trim());
                            mBinding.tvDate.setText("");
                            break;
                        case 2:
                            mChild.setBaseHearing(mBinding.tvDate.getText().toString().trim());
                            mBinding.tvDate.setText("");
                            break;
                        case 3:
                            int selectedId = mBinding.rgGroup.getCheckedRadioButtonId();
                            int choice;
                            switch (selectedId) {
                                case R.id.rb_option_one:
                                    choice = 0;
                                    mChild.setBaseHearingResult(choice);
                                    break;
                                case R.id.rb_option_two:
                                    choice = 1;
                                    mChild.setBaseHearingResult(choice);
                                    break;
                                case R.id.rb_option_three:
                                    choice = 2;
                                    mChild.setBaseHearingResult(choice);
                                    break;
                            }
                            break;
                        case 4:
                            mChild.setBaseNextHearing(mBinding.tvDate.getText().toString().trim());
                            mBinding.tvDate.setText("");
                            break;
                        case 5:
                            mChild.setBaseVision(mBinding.tvDate.getText().toString().trim());
                            mBinding.tvDate.setText("");
                            break;
                        case 6:
                            mChild.setBaseHGB(mBinding.tvDate.getText().toString().trim());
                            mBinding.tvDate.setText("");
                            break;
                        case 7:
                            mChild.setBaseTSH(mBinding.tvDate.getText().toString().trim());
                            mBinding.tvDate.setText("");
                            break;
                    }
                    questionCounter++;
                    showNextQuestion(mRoutineScreeningQuestionList);
                } else {
                    Toasty.error(getActivity(), "Please answer the question!" ,Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ScreeningQuestionsViewModel.class);
        mViewModel.getRoutineQuestionList().observe(getViewLifecycleOwner(), new Observer<List<RoutineScreeningQuestion>>() {
            @Override
            public void onChanged(List<RoutineScreeningQuestion> routineScreeningQuestions) {
                if (routineScreeningQuestions != null) {
                    mRoutineScreeningQuestionList = routineScreeningQuestions;
                    questionTotal = routineScreeningQuestions.size();
                    showNextQuestion(routineScreeningQuestions);
                }
            }
        });
    }

    private void showNextQuestion(List<RoutineScreeningQuestion> routineScreeningQuestions) {
        if (questionCounter < questionTotal) {
            currentQuestion = routineScreeningQuestions.get(questionCounter);
            mBinding.tvQuestion.setText(currentQuestion.getQuestion());
            mBinding.layoutDate.setVisibility(View.VISIBLE);
            mBinding.rgGroup.setVisibility(View.GONE);
            mBinding.pbQuestion.setProgress(questionCounter);

            if (questionCounter == 3) {
                mBinding.layoutDate.setVisibility(View.GONE);
                mBinding.rgGroup.setVisibility(View.VISIBLE);
                mBinding.pbQuestion.setProgress(questionCounter);
            }


        } else if (questionCounter == questionTotal) {
            Toasty.success(getActivity(), "All questions answered!", Toast.LENGTH_SHORT, true).show();
            mBinding.tvQuestion.setVisibility(View.GONE);
            mBinding.layoutDate.setVisibility(View.GONE);
            mBinding.bvNextQuestion.setVisibility(View.GONE);
            mBinding.bvGoToMedical.setVisibility(View.VISIBLE);
            mBinding.pbQuestion.setProgress(8);
            Log.d(TAG, "showNextQuestion: " + mChild.getBaseWCC() + mChild.getBaseNextWCC() + mChild.getBaseHearing() + mChild.getBaseHearingResult() + mChild.getBaseNextHearing() + mChild.getBaseVision() + mChild.getBaseHGB() + mChild.getBaseTSH());
        }
    }

    private void goToMedicalQuestionsFragment() {
        mBinding.bvGoToMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("child", mChild);
                Navigation.findNavController(getView()).navigate(R.id.nav_child_medical , bundle);
            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int Month = month + 1;
        String date = Month + "/" + dayOfMonth + "/" + year;
        mBinding.tvDate.setText(date);
    }
}