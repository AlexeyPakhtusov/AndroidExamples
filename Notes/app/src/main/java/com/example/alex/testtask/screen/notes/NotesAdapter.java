package com.example.alex.testtask.screen.notes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.testtask.R;
import com.example.alex.testtask.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesHolder> {

    private List<Note> mNotes = new ArrayList<>();

    @Nullable
    private OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalClickListener = (view) -> {
        if (mOnItemClickListener != null) {
            int position = (int) view.getTag();
            mOnItemClickListener.onItemClick(getItem(position));
        }
    };

    public NotesAdapter(@NonNull List<Note> notes,
                        @Nullable OnItemClickListener onItemClickListener) {
        mNotes.addAll(notes);
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public NotesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NotesHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(NotesHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(mInternalClickListener);
        holder.bind(getItem(position));
    }

    @NonNull
    public Note getItem(int position) {
        return mNotes.get(position);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void changeDataSet(@NonNull List<Note> notes) {
        mNotes.clear();
        mNotes.addAll(notes);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {

        void onItemClick(@NonNull Note note);
    }
}
