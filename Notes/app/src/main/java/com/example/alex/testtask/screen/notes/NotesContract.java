package com.example.alex.testtask.screen.notes;

import android.support.annotation.NonNull;

import com.example.alex.testtask.model.Note;

import java.util.List;

public interface NotesContract {

    interface View {

        void showError();

        void showNotes(@NonNull List<Note> notes);

        void showAddNote();

        void showEditNote(@NonNull Note note);
    }

    interface Presenter {

        void loadNotes();

        void addNewNote();

        void editNote(@NonNull Note note);
    }
}
