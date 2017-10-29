package com.example.alex.testtask.screen.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.alex.testtask.R;
import com.example.alex.testtask.model.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.note_title)
    TextView mTitle;

    @BindView(R.id.note_description)
    TextView mDescription;

    public NotesHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Note note) {
        mTitle.setText(note.getTitle());
        mDescription.setText(note.getDescription());
    }
}
