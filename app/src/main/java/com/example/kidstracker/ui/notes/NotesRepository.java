package com.example.kidstracker.ui.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.kidstracker.database.AppExecutors;
import com.example.kidstracker.database.KidsTrackingDatabase;
import com.example.kidstracker.database.dao.NoteDao;
import com.example.kidstracker.models.Note;

import java.util.List;

public class NotesRepository {
    private NoteDao mNoteDao;
    private LiveData<List<Note>> allNotes;

    public NotesRepository(Application application) {
        KidsTrackingDatabase kidsTrackingDatabase = KidsTrackingDatabase.getInstance(application);
        mNoteDao = kidsTrackingDatabase.noteDao();
        allNotes = mNoteDao.getAllNotes();
    }

    public void insert(Note note) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.insert(note);
            }
        });
    }

    public void update(Note note) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.update(note);
            }
        });
    }

    public void delete(Note note) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.delete(note);
            }
        });
    }

    public void deleteAllNotes() {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.deleteAllNotes();
            }
        });
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
