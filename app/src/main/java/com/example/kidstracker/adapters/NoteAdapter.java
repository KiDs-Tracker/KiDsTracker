package com.example.kidstracker.adapters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidstracker.R;
import com.example.kidstracker.models.Note;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    private OnNoteClickListener mNoteClickListener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_item, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = getItem(position);
        holder.mTitleTextView.setText(note.getTitle());
        holder.mDescriptionTextView.setText(note.getDescription());
        holder.mPriorityTextView.setText(String.valueOf(note.getPriority()));
        GradientDrawable gradientDrawable = (GradientDrawable) holder.mPriorityTextView.getBackground();
        gradientDrawable.setColor(getColor(note));

    }

    private int getColor(Note note) {
        int priorityColorId = 0;
        switch (note.getPriority()) {
            case 1:
                priorityColorId = R.color.priority1;
                break;
            case 2:
                priorityColorId = R.color.priority2;
                break;
            case 3:
                priorityColorId = R.color.priority3;
                break;
            case 4:
                priorityColorId = R.color.priority4;
                break;
            case 5:
                priorityColorId = R.color.priority5;
                break;
            case 6:
                priorityColorId = R.color.priority6;
                break;
            case 7:
                priorityColorId = R.color.priority7;
                break;
            case 8:
                priorityColorId = R.color.priority8;
                break;
            case 9:
                priorityColorId = R.color.priority9;
                break;
            case 10:
                priorityColorId = R.color.priority10plus;
                break;
        }
        return priorityColorId;
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTextView;
        private TextView mDescriptionTextView;
        private TextView mPriorityTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.tv_title);
            mDescriptionTextView = itemView.findViewById(R.id.tv_description);
            mPriorityTextView = itemView.findViewById(R.id.tv_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mNoteClickListener != null && position != RecyclerView.NO_POSITION) {
                        mNoteClickListener.onNoteClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public void setOnNoteClickListener(OnNoteClickListener listener) {
        mNoteClickListener = listener;
    }
}
