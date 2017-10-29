package com.example.alex.testtask.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public class RepositoryProvider {

    private static NotesRepository sNotesRepository;

    private RepositoryProvider() {
    }

    @NonNull
    public static NotesRepository provideNotesRepository() {
        if (sNotesRepository == null) {
            sNotesRepository = new DefaultNotesRepository();
        }

        return sNotesRepository;
    }

    public static void setNotesRepository(@NonNull NotesRepository notesRepository) {
        sNotesRepository = notesRepository;
    }

    @MainThread
    public static void init() {
        setNotesRepository(new DefaultNotesRepository());
    }
}
