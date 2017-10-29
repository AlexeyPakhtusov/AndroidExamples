package com.example.alex.testtask.screen.note;

import android.support.annotation.NonNull;

import com.example.alex.testtask.model.Note;

public interface NoteContract {

    interface View {

        void onSaveNote(@NonNull Note note);

        void showError();
    }

    interface Presenter {

        void saveNote(@NonNull Note note);
    }
}
