package com.example.alex.testtask.screen;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.alex.testtask.R;
import com.example.alex.testtask.model.Note;
import com.example.alex.testtask.screen.note.NoteView;
import com.example.alex.testtask.screen.notes.NotesView;

public class Base extends AppCompatActivity implements NotesView.NotesListener,
                                                       NoteView.NoteListener {

    private static final String FRAGMENT_NOTES = "fragment_notes";
    private static final String FRAGMENT_NOTE = "fragment_note";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notes);

        if (null == savedInstanceState) {
            initFragment(NotesView.newInstance());
        }
    }

    private void initFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment, FRAGMENT_NOTES)
                .commit();
    }

    @Override
    public void onShowAddNote() {
        showNote(NoteView.newInstance(new Note(null, null)));
    }

    @Override
    public void onShowEditNote(@NonNull Note note) {
        showNote(NoteView.newInstance(note));
    }

    private void showNote(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment, FRAGMENT_NOTE)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void onShowNotes() {
        View view = getCurrentFocus();
        if (view != null) {
            IBinder iBinder = view.getWindowToken();
            if (iBinder != null) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(iBinder, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        ((NotesView) fragmentManager.findFragmentByTag(FRAGMENT_NOTES)).forceLoad();
    }
}
