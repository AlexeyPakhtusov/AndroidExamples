package com.example.alex.testtask.repository;

import android.support.annotation.NonNull;

import com.example.alex.testtask.model.Note;

import java.util.List;

import io.reactivex.Observable;

public interface NotesRepository {

    Observable<List<Note>> getNotes();

    Observable<Note> addNote(@NonNull Note note);

    Observable<Note> updateNote(@NonNull Note note);

    Observable<Note> deleteNote(@NonNull Note note);
}
