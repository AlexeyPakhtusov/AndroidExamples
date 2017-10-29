package com.example.alex.testtask.screen.note;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alex.testtask.R;
import com.example.alex.testtask.model.Note;
import com.example.alex.testtask.utils.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteView extends Fragment implements NoteContract.View {

    private static final String EDITABLE_NOTE = "editable_note";

    private Note mNote;

    private NoteListener mNoteListener;

    private NoteContract.Presenter mPresenter;

    private FloatingActionButton mFloatingActionButton;

    @BindView(R.id.editable_title)
    EditText mEditTitle;

    @BindView(R.id.editable_description)
    EditText mEditDescription;

    @BindView(R.id.date_created)
    TextView mTextDateCreated;

    public NoteView() {
    }

    public static NoteView newInstance(@Nullable Note note) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EDITABLE_NOTE, note);
        NoteView noteView = new NoteView();
        noteView.setArguments(bundle);
        return noteView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mNoteListener = (NoteListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mNote = (Note) getArguments().getSerializable(EDITABLE_NOTE);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        mPresenter = new NotePresenter(this);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note, container, false);

        ButterKnife.bind(this, root);

        if (mNote == null) {
            mNote = new Note(null, null);
        }
        mEditTitle.setText(mNote.getTitle());
        mEditDescription.setText(mNote.getDescription());
        mTextDateCreated.setText(DateUtil.normalizeDate(mNote.getDateCreated()));

        mFloatingActionButton = getActivity().findViewById(R.id.fab_btn);
        mFloatingActionButton.setOnClickListener(view -> saveNote());

        return root;
    }

    private void saveNote() {
        mNote.setTitle(mEditTitle.getText().toString());
        mNote.setDescription(mEditDescription.getText().toString());
        mPresenter.saveNote(mNote);
    }

    @Override
    public void onSaveNote(@NonNull Note note) {
        showSnack(getActivity().getResources().getString(R.string.note_save_successful));
        mNoteListener.onShowNotes();
    }

    @Override
    public void showError() {
        showSnack(getActivity().getResources().getString(R.string.note_error));
    }

    private void showSnack(String message) {
        Snackbar.make(mFloatingActionButton, message, Snackbar.LENGTH_SHORT).show();
    }

    public interface NoteListener {

        void onShowNotes();
    }
}
