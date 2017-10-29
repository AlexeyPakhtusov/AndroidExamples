package com.example.alex.testtask.screen.note;

import android.support.annotation.NonNull;

import com.example.alex.testtask.model.Note;
import com.example.alex.testtask.repository.NotesRepository;
import com.example.alex.testtask.repository.RepositoryProvider;

import io.reactivex.Observable;

public class NotePresenter implements NoteContract.Presenter {

    private NoteContract.View mView;

    public NotePresenter(NoteContract.View view) {
        mView = view;
    }

    @Override
    public void saveNote(@NonNull Note note) {
        if (note.getTitle() == null || note.getDescription() == null ||
            "".equals(note.getTitle()) || "".equals(note.getDescription())) {
            mView.showError();
        } else {
            futureSave(note).subscribe(mView::onSaveNote, throwable -> mView.showError());
        }
    }

    private Observable<Note> futureSave(@NonNull Note note) {
        NotesRepository repository = RepositoryProvider.provideNotesRepository();
        if (note.getId() != null) {
            return repository.updateNote(note);
        } else {
            return repository.addNote(note);
        }
    }
}
