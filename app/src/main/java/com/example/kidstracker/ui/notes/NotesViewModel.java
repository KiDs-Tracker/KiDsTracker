package com.example.kidstracker.ui.notes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.kidstracker.models.Note;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private NotesRepository mNotesRepository;
    private LiveData<List<Note>> allNotes;

    public NotesViewModel(Application application) {
        super(application);
        mNotesRepository = new NotesRepository(application);
        allNotes = mNotesRepository.getAllNotes();
    }

    public void insert(Note note) {
        mNotesRepository.insert(note);
    }

    public void update(Note note) {
        mNotesRepository.update(note);
    }

    public void delete(Note note) {
        mNotesRepository.delete(note);
    }

    public void deleteAllNotes() {
        mNotesRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}