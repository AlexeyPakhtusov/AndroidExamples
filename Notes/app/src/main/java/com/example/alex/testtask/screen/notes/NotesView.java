package com.example.alex.testtask.screen.notes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.testtask.R;
import com.example.alex.testtask.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesView extends Fragment implements NotesContract.View,
                                                   NotesAdapter.OnItemClickListener {

    private NotesListener mNotesListener;

    private NotesContract.Presenter mPresenter;

    private NotesAdapter mAdapter;

    private FloatingActionButton mFloatingActionButton;

    public NotesView() {
    }

    public static NotesView newInstance() {
        return new NotesView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mNotesListener = (NotesListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new NotesPresenter(this);
        mAdapter = new NotesAdapter(new ArrayList<>(), this);

        forceLoad();

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        recyclerView.setAdapter(mAdapter);

        mFloatingActionButton = getActivity().findViewById(R.id.fab_btn);
        mFloatingActionButton.setOnClickListener((view) -> mPresenter.addNewNote());

        return root;
    }

    public void forceLoad() {
        mPresenter.loadNotes();
    }

    @Override
    public void onItemClick(@NonNull Note note) {
        mPresenter.editNote(note);
    }

    @Override
    public void showError() {
        Snackbar.make(mFloatingActionButton, getActivity().getResources().getString(R.string.notes_error), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showNotes(@NonNull List<Note> notes) {
        mAdapter.changeDataSet(notes);
    }

    @Override
    public void showAddNote() {
        mNotesListener.onShowAddNote();
    }

    @Override
    public void showEditNote(@NonNull Note note) {
        mNotesListener.onShowEditNote(note);
    }

    public interface NotesListener {

        void onShowAddNote();

        void onShowEditNote(@NonNull Note note);
    }
}
