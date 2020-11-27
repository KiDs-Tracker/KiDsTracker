package com.example.kidstracker.ui.childregistration.medicalquestions;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.MedicalQuestionsFragmentBinding;
import com.example.kidstracker.models.Child;
import com.example.kidstracker.models.MedicalQuestion;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class MedicalQuestionsFragment extends Fragment {

    public static final String TAG = MedicalQuestionsFragment.class.getSimpleName();

    private MedicalQuestionsViewModel mViewModel;
    private MedicalQuestionsFragmentBinding mBinding;

    private Child mChild;
    private List<MedicalQuestion> mMedicalQuestionList;

    private int questionCounter;
    private int questionTotal;
    private MedicalQuestion currentQuestion;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.medical_questions_fragment, container, false);
        View root = mBinding.getRoot();

        mChild = (Child) getArguments().getSerializable("child");

        onNextQuestionClick();
        goToHomeFragment();

        return root;
    }

    private void goToHomeFragment() {
        mBinding.bvGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.insertChild(mChild);
                Navigation.findNavController(v).navigate(R.id.nav_home);
            }
        });
    }

    private void onNextQuestionClick() {
        mBinding.bvNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.rbOptionOne.isChecked() || mBinding.rbOptionTwo.isChecked() || mBinding.rbOptionThree.isChecked()) {
                    switch(questionCounter) {
                        case 0:
                            mChild.setPriorSurgeries(getOptionChoice());
                            break;
                        case 1:
                            mChild.setPriorEyeProblems(getOptionChoice());
                            break;
                        case 2:
                            mChild.setPriorHearingProblems(getOptionChoice());
                            break;
                        case 3:
                            mChild.setPriorNeckProblems(getOptionChoice());
                            break;
                        case 4:
                            mChild.setPriorThyroidProblems(getOptionChoice());
                            break;
                        case 5:
                            mChild.setPriorHeartProblems(getOptionChoice());
                            break;
                        case 6:
                            mChild.setPriorBreathingProblems(getOptionChoice());
                            break;
                        case 7:
                            mChild.setPriorConstipationProblems(getOptionChoice());
                            break;
                        case 8:
                            mChild.setPriorSleepingProblems(getOptionChoice());
                            break;
                        case 9:
                            mChild.setPriorBrainProblems(getOptionChoice());
                            break;
                        case 10:
                            mChild.setPriorBloodProblems(getOptionChoice());
                            break;
                        case 11:
                            mChild.setPriorImmuneProblems(getOptionChoice());
                            break;
                    }
                    questionCounter++;
                    mBinding.rbOptionOne.setChecked(false);
                    mBinding.rbOptionTwo.setChecked(false);
                    mBinding.rbOptionThree.setChecked(false);
                    showNextQuestion(mMedicalQuestionList);
                } else {
                    Toasty.error(getActivity(), "Please answer the question!" ,Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    private int getOptionChoice() {
        int selectedId = mBinding.rgGroup.getCheckedRadioButtonId();
        int choice = 0;
        switch (selectedId) {
            case R.id.rb_option_one:
                choice = 0;
                break;
            case R.id.rb_option_two:
                choice = 1;
                break;
            case R.id.rb_option_three:
                choice = 2;
                break;
        }
        return choice;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MedicalQuestionsViewModel.class);
        mViewModel.getMedicalQuestionList().observe(getViewLifecycleOwner(), new Observer<List<MedicalQuestion>>() {
            @Override
            public void onChanged(List<MedicalQuestion> medicalQuestions) {
                if (medicalQuestions != null) {
                    mMedicalQuestionList = medicalQuestions;
                    questionTotal = medicalQuestions.size();
                    showNextQuestion(medicalQuestions);
                }
            }
        });
    }

    private void showNextQuestion(List<MedicalQuestion> medicalQuestions) {
        if (questionCounter < questionTotal) {
            currentQuestion = medicalQuestions.get(questionCounter);
            mBinding.tvQuestion.setText(currentQuestion.getQuestion());
            mBinding.pbQuestion.setProgress(questionCounter);
        } else {
            Toasty.success(getActivity(), "All questions answered!", Toast.LENGTH_SHORT, true).show();
            mBinding.tvQuestion.setVisibility(View.GONE);
            mBinding.bvNextQuestion.setVisibility(View.GONE);
            mBinding.rgGroup.setVisibility(View.GONE);
            mBinding.bvGoToHome.setVisibility(View.VISIBLE);
            mBinding.pbQuestion.setProgress(12);
            /*Log.d(TAG, "showNextQuestion: " + "\n" +
                    mChild.getPriorSurgeries() + "\n" +
                    mChild.getPriorEyeProblems() + "\n" +
                    mChild.getPriorHearingProblems() + "\n" +
                    mChild.getPriorNeckProblems() + "\n" +
                    mChild.getPriorThyroidProblems() + "\n" +
                    mChild.getPriorHeartProblems() + "\n" +
                    mChild.getPriorBreathingProblems() + "\n" +
                    mChild.getPriorConstipationProblems() + "\n" +
                    mChild.getPriorSleepingProblems() + "\n" +
                    mChild.getPriorBrainProblems() + "\n" +
                    mChild.getPriorBloodProblems() + "\n" +
                    mChild.getPriorImmuneProblems()); */
        }
    }

}