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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidstracker.R;
import com.example.kidstracker.adapters.NoteAdapter;
import com.example.kidstracker.databinding.FragmentNotesBinding;
import com.example.kidstracker.models.Note;

import java.util.List;

public class NotesFragment extends Fragment {

    private NotesViewModel mViewModel;
    private FragmentNotesBinding mBinding;
    private NoteAdapter mAdapter = new NoteAdapter();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false);
        View root = mBinding.getRoot();

        setHasOptionsMenu(true);

        setAdapter();

        mBinding.btAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.nav_add_notes);
            }
        });

        mViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                mAdapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.delete(mAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mBinding.rvNotes);

        mAdapter.setOnNoteClickListener(new NoteAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", note.getId());
                bundle.putString("title", note.getTitle());
                bundle.putString("description", note.getDescription());
                bundle.putInt("priority", note.getPriority());

                Navigation.findNavController(getView()).navigate(R.id.nav_add_notes, bundle);
            }
        });

        return root;
    }

    private void setAdapter() {
        mBinding.rvNotes.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvNotes.setHasFixedSize(true);
        mBinding.rvNotes.setAdapter(mAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notes_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                mViewModel.deleteAllNotes();
                Toast.makeText(getContext(), "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}