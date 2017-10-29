package com.example.alex.testtask.screen.notes;

import android.support.annotation.NonNull;

import com.example.alex.testtask.model.Note;
import com.example.alex.testtask.repository.RepositoryProvider;

public class NotesPresenter implements NotesContract.Presenter {

    private NotesContract.View mView;

    public NotesPresenter(NotesContract.View view) {
        mView = view;
    }

    @Override
    public void loadNotes() {
        RepositoryProvider.provideNotesRepository()
                .getNotes()
                .subscribe(mView::showNotes, throwable -> mView.showError());
    }

    @Override
    public void addNewNote() {
        mView.showAddNote();
    }

    @Override
    public void editNote(@NonNull Note note) {
        mView.showEditNote(note);
    }
}
