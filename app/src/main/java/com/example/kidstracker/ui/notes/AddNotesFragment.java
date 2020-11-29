package com.example.kidstracker.ui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.kidstracker.R;
import com.example.kidstracker.databinding.FragmentAddNotesBinding;
import com.example.kidstracker.models.Note;

import es.dmoral.toasty.Toasty;

public class AddNotesFragment extends Fragment {

    private FragmentAddNotesBinding mBinding;
    private NotesViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment__add_notes, container, false);

        View rootView = mBinding.getRoot();

        setHasOptionsMenu(true);
        setNumberPickerPriority();

        if (!(getArguments() == null)) {
            setAddEditNotes();
        }

        return rootView;
    }

    private void setAddEditNotes() {
        mBinding.etvTitle.setText(getArguments().getString("title"));
        mBinding.etvDescription.setText(getArguments().getString("description"));
        mBinding.npPriority.setValue(getArguments().getInt("priority"));
    }

    private void setNumberPickerPriority() {
        mBinding.npPriority.setMinValue(1);
        mBinding.npPriority.setMaxValue(10);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_note_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = mBinding.etvTitle.getText().toString();
        String description = mBinding.etvDescription.getText().toString();
        int priority = mBinding.npPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toasty.error(getContext(), "Please insert a title and/or description", Toast.LENGTH_SHORT, true).show();
            return;
        }

        Note note = new Note(title, description, priority);
        if (!(getArguments() == null)) {
            note.setId(getArguments().getInt("id"));
            mViewModel.update(note);
        } else {
            mViewModel.insert(note);
        }
        Navigation.findNavController(getView()).navigate(R.id.nav_notes);
    }
}