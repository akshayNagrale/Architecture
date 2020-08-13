package com.example.architecture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private onItemClickListener listener;
    private List<Note> note = new ArrayList<>();
    private Context context;

    public NoteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(note.get(position).getTitle());
        holder.txtDescription.setText(note.get(position).getDescription());
        holder.textPriority.setText(String.valueOf(note.get(position).getPriority()));


    }

    @Override
    public int getItemCount() {
        return  note.size();
    }

    public void setNote(List<Note> note) {
        this.note = note;
        notifyDataSetChanged();
    }
    public Note getNoteAt(int position){
        return note.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, textPriority, txtDescription;
        public ViewHolder(@NonNull View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtTitle);
            textPriority = view.findViewById(R.id.txtPriority);
            txtDescription = view.findViewById(R.id.txtDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION);{
                        listener.onItemClick(note.get(position));
                    }

                }
            });
        }
    }
    public interface onItemClickListener {
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;

    }
}
