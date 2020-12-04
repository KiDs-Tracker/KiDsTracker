package com.example.kidstracker.ui.children.editchild;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.FragmentEditChildBinding;
import com.example.kidstracker.models.Child;
import com.example.kidstracker.ui.childregistration.ChildRegistrationFragment;

import java.util.Calendar;


public class EditChildFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private FragmentEditChildBinding mBinding;
    private EditChildViewModel mViewModel;

    private Child mChild;
    private String date;

    private boolean age = false;
    private boolean baseWCC = false;
    private boolean baseNextWCC = false;
    private boolean baseHearing = false;
    private boolean baseNextHear = false;
    private boolean baseVision = false;
    private boolean baseHGB = false;
    private boolean baseTSH = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_child, container, false);
        View root = mBinding.getRoot();

        assert getArguments() != null;
        mChild = (Child) getArguments().getSerializable("my_child");

        setUpDateUI();
        setUpPromptUI();

        mBinding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMedicalQuestions();
            }
        });

        mBinding.btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRoutineQuestions();
            }
        });

        mBinding.fbSaveChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChild();
            }
        });

        onGenderButtonClick();
        onDateCalendarButtonClick();
        onListButtonClick();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditChildViewModel.class);
    }

    private void updateChild() {
        Child child = new Child();

        child.setId(mChild.getId());
        child.setFirstName(mChild.getFirstName());
        child.setLastName(mChild.getLastName());
        child.setGender(mChild.getGender());
        child.setAge(mChild.getAge());
        child.setDiagnosis(mChild.getDiagnosis());

        child.setBaseWCC(mChild.getBaseWCC());
        child.setBaseNextWCC(mChild.getBaseNextWCC());
        child.setBaseHearing(mChild.getBaseHearing());
        child.setBaseHearingResult(mChild.getBaseHearingResult());
        child.setBaseNextHearing(mChild.getBaseNextHearing());
        child.setBaseVision(mChild.getBaseVision());
        child.setBaseHGB(mChild.getBaseHGB());
        child.setBaseTSH(mChild.getBaseTSH());

        child.setPriorSurgeries(mChild.getPriorSurgeries());
        child.setPriorEyeProblems(mChild.getPriorEyeProblems());
        child.setPriorHearingProblems(mChild.getPriorHearingProblems());
        child.setPriorNeckProblems(mChild.getPriorNeckProblems());
        child.setPriorThyroidProblems(mChild.getPriorThyroidProblems());
        child.setPriorHeartProblems(mChild.getPriorHeartProblems());
        child.setPriorBreathingProblems(mChild.getPriorBreathingProblems());
        child.setPriorConstipationProblems(mChild.getPriorConstipationProblems());
        child.setPriorSleepingProblems(mChild.getPriorSleepingProblems());
        child.setPriorBrainProblems(mChild.getPriorBrainProblems());
        child.setPriorBloodProblems(mChild.getPriorBloodProblems());
        child.setPriorImmuneProblems(mChild.getPriorImmuneProblems());
        child.setMedications(mBinding.tvMedicationAnswer.getText().toString().trim());

        mViewModel.updateChild(child);
        Navigation.findNavController(getView()).navigate(R.id.nav_children);
    }

    private void onGenderButtonClick() {
        mBinding.ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pick a gender")
                        .setItems(R.array.gender_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvGender.setText("Male");
                                        mBinding.ivGender.setImageResource(R.drawable.ic_male);
                                        mChild.setGender(0);
                                        break;
                                    case 1:
                                        mBinding.tvGender.setText("Female");
                                        mBinding.ivGender.setImageResource(R.drawable.ic_female);
                                        mChild.setGender(1);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

    }

    private void onListButtonClick() {
        mBinding.ib6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvHearingTestResult.setText("Yes");
                                        mChild.setBaseHearingResult(0);
                                        break;
                                    case 1:
                                        mBinding.tvHearingTestResult.setText("No");
                                        mChild.setBaseHearingResult(1);
                                        break;
                                    case 2:
                                        mBinding.tvHearingTestResult.setText("Don't know");
                                        mChild.setBaseHearingResult(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvPriorSurgeriesAnswer.setText("Yes");
                                        mChild.setPriorSurgeries(0);
                                        break;
                                    case 1:
                                        mBinding.tvPriorSurgeriesAnswer.setText("No");
                                        mChild.setPriorSurgeries(1);
                                        break;
                                    case 2:
                                        mBinding.tvPriorSurgeriesAnswer.setText("Don't know");
                                        mChild.setPriorSurgeries(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvEyeAnswer.setText("Yes");
                                        mChild.setPriorEyeProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvEyeAnswer.setText("No");
                                        mChild.setPriorEyeProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvEyeAnswer.setText("Don't know");
                                        mChild.setPriorEyeProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvHearingAnswer.setText("Yes");
                                        mChild.setPriorHearingProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvHearingAnswer.setText("No");
                                        mChild.setPriorHearingProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvHearingAnswer.setText("Don't know");
                                        mChild.setPriorHearingProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvNeckAnswer.setText("Yes");
                                        mChild.setPriorNeckProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvNeckAnswer.setText("No");
                                        mChild.setPriorNeckProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvNeckAnswer.setText("Don't know");
                                        mChild.setPriorNeckProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvThyroidAnswer.setText("Yes");
                                        mChild.setPriorThyroidProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvThyroidAnswer.setText("No");
                                        mChild.setPriorThyroidProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvThyroidAnswer.setText("Don't know");
                                        mChild.setPriorThyroidProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvHeartAnswer.setText("Yes");
                                        mChild.setPriorHeartProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvHeartAnswer.setText("No");
                                        mChild.setPriorHeartProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvHeartAnswer.setText("Don't know");
                                        mChild.setPriorHeartProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvBreathingAnswer.setText("Yes");
                                        mChild.setPriorBreathingProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvBreathingAnswer.setText("No");
                                        mChild.setPriorBreathingProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvBreathingAnswer.setText("Don't know");
                                        mChild.setPriorBreathingProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvConstipationAnswer.setText("Yes");
                                        mChild.setPriorConstipationProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvConstipationAnswer.setText("No");
                                        mChild.setPriorConstipationProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvConstipationAnswer.setText("Don't know");
                                        mChild.setPriorConstipationProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvSleepAnswer.setText("Yes");
                                        mChild.setPriorSleepingProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvSleepAnswer.setText("No");
                                        mChild.setPriorSleepingProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvSleepAnswer.setText("Don't know");
                                        mChild.setPriorSleepingProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvBrainAnswer.setText("Yes");
                                        mChild.setPriorBrainProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvBrainAnswer.setText("No");
                                        mChild.setPriorBrainProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvBrainAnswer.setText("Don't know");
                                        mChild.setPriorBrainProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvBloodAnswer.setText("Yes");
                                        mChild.setPriorBloodProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvBloodAnswer.setText("No");
                                        mChild.setPriorBloodProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvBloodAnswer.setText("Don't know");
                                        mChild.setPriorBloodProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        mBinding.ibList12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose an Option")
                        .setItems(R.array.choice_list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mBinding.tvImmuneAnswer.setText("Yes");
                                        mChild.setPriorImmuneProblems(0);
                                        break;
                                    case 1:
                                        mBinding.tvImmuneAnswer.setText("No");
                                        mChild.setPriorImmuneProblems(1);
                                        break;
                                    case 2:
                                        mBinding.tvImmuneAnswer.setText("Don't know");
                                        mChild.setPriorImmuneProblems(2);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });
    }

    private void setUpPromptUI() {
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
                break;
        }

        mBinding.tvMedicationAnswer.setText(mChild.getMedications());
    }

    private void setUpDateUI() {
        String name = mChild.getFirstName() + " " + mChild.getLastName();
        mBinding.tvName.setText(name);
        mBinding.tvAge.setText(mChild.getAge());

        switch (mChild.getGender()) {
            case 0:
                mBinding.tvGender.setText("Male");
                mBinding.ivGender.setImageResource(R.drawable.ic_male);
                break;
            case 1:
                mBinding.tvGender.setText("Female");
                mBinding.ivGender.setImageResource(R.drawable.ic_female);
                break;
        }

        mBinding.tvLastWcDate.setText(mChild.getBaseWCC());
        mBinding.tvNextWcDate.setText(mChild.getBaseNextWCC());
        mBinding.tvLastHearingDate.setText(mChild.getBaseHearing());
        switch (mChild.getBaseHearingResult()) {
            case 0:
                mBinding.tvHearingTestResult.setText("Yes");
                break;
            case 1:
                mBinding.tvHearingTestResult.setText("No");
                break;
            case 2:
                mBinding.tvHearingTestResult.setText("Don't Know");
                break;
        }
        mBinding.tvNextHearingDate.setText(mChild.getBaseNextHearing());
        mBinding.tvLastEyeDate.setText(mChild.getBaseVision());
        mBinding.tvLastBloodIronDate.setText(mChild.getBaseHGB());
        mBinding.tvLastBloodThyroidDate.setText(mChild.getBaseTSH());
    }

    private void setVisibilityOffForMed() {
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

        mBinding.ibList1.setVisibility(View.GONE);
        mBinding.ibList2.setVisibility(View.GONE);
        mBinding.ibList3.setVisibility(View.GONE);
        mBinding.ibList4.setVisibility(View.GONE);
        mBinding.ibList5.setVisibility(View.GONE);
        mBinding.ibList6.setVisibility(View.GONE);
        mBinding.ibList7.setVisibility(View.GONE);
        mBinding.ibList8.setVisibility(View.GONE);
        mBinding.ibList9.setVisibility(View.GONE);
        mBinding.ibList10.setVisibility(View.GONE);
        mBinding.ibList11.setVisibility(View.GONE);
        mBinding.ibList12.setVisibility(View.GONE);
    }

    private void setVisibilityForMed() {
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

        mBinding.ibList1.setVisibility(View.VISIBLE);
        mBinding.ibList2.setVisibility(View.VISIBLE);
        mBinding.ibList3.setVisibility(View.VISIBLE);
        mBinding.ibList4.setVisibility(View.VISIBLE);
        mBinding.ibList5.setVisibility(View.VISIBLE);
        mBinding.ibList6.setVisibility(View.VISIBLE);
        mBinding.ibList7.setVisibility(View.VISIBLE);
        mBinding.ibList8.setVisibility(View.VISIBLE);
        mBinding.ibList9.setVisibility(View.VISIBLE);
        mBinding.ibList10.setVisibility(View.VISIBLE);
        mBinding.ibList11.setVisibility(View.VISIBLE);
        mBinding.ibList12.setVisibility(View.VISIBLE);
    }

    private void onDateCalendarButtonClick() {
        mBinding.ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendarDialogue();
                age = true;
            }
        });

        mBinding.ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendarDialogue();
                baseWCC = true;
            }
        });

        mBinding.ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendarDialogue();
                baseNextWCC = true;
            }
        });

        mBinding.ib5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendarDialogue();
                baseHearing = true;
            }
        });

        mBinding.ib7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendarDialogue();
                baseNextHear = true;
            }
        });

        mBinding.ib8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendarDialogue();
                baseVision = true;
            }
        });

        mBinding.ib9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendarDialogue();
                baseHGB = true;
            }
        });

        mBinding.ib10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendarDialogue();
                baseTSH = true;
            }
        });
    }

    private void setUpCalendarDialogue() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                EditChildFragment.this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void goToRoutineQuestions() {
        mBinding.vfChangeQuestions.showPrevious();
        mBinding.scrollView.fullScroll(View.FOCUS_UP);
        setVisibilityOffForMed();
    }

    private void goToMedicalQuestions() {
        mBinding.vfChangeQuestions.showNext();
        mBinding.scrollView.fullScroll(View.FOCUS_UP);
        setVisibilityForMed();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int Month = month + 1;
        date = Month + "/" + dayOfMonth + "/" + year;
        String newDate = dayOfMonth + "/" + Month + "/" + year;;
        if (age && !baseWCC && !baseNextWCC && !baseHearing && !baseNextHear && !baseVision && !baseHGB && !baseTSH) {
            mBinding.tvAge.setText(date);
            mChild.setAge(newDate);
            age = false;
        } else if (!age && baseWCC && !baseNextWCC && !baseHearing && !baseNextHear && !baseVision && !baseHGB && !baseTSH) {
            mBinding.tvLastWcDate.setText(date);
            mChild.setBaseWCC(newDate);
            baseWCC = false;
        } else if (!age && !baseWCC && baseNextWCC && !baseHearing && !baseNextHear && !baseVision && !baseHGB && !baseTSH) {
            mBinding.tvNextWcDate.setText(date);
            mChild.setBaseNextWCC(newDate);
            baseNextWCC = false;
        } else if (!age && !baseWCC && !baseNextWCC && baseHearing && !baseNextHear && !baseVision && !baseHGB && !baseTSH) {
            mBinding.tvLastHearingDate.setText(date);
            mChild.setBaseHearing(newDate);
            baseHearing = false;
        } else if (!age && !baseWCC && !baseNextWCC && !baseHearing && baseNextHear && !baseVision && !baseHGB && !baseTSH) {
            mBinding.tvNextHearingDate.setText(date);
            mChild.setBaseNextHearing(newDate);
            baseNextHear = false;
        } else if (!age && !baseWCC && !baseNextWCC && !baseHearing && !baseNextHear && baseVision && !baseHGB && !baseTSH) {
            mBinding.tvLastEyeDate.setText(date);
            mChild.setBaseVision(newDate);
            baseVision = false;
        } else if (!age && !baseWCC && !baseNextWCC && !baseHearing && !baseNextHear && !baseVision && baseHGB && !baseTSH) {
            mBinding.tvLastBloodIronDate.setText(date);
            mChild.setBaseHGB(newDate);
            baseHGB = false;
        } else if (!age && !baseWCC && !baseNextWCC && !baseHearing && !baseNextHear && !baseVision && !baseHGB && baseTSH) {
            mBinding.tvLastBloodThyroidDate.setText(date);
            mChild.setBaseTSH(newDate);
            baseTSH = false;
        }
    }
}