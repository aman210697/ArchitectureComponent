package com.aman.architecturecomponent;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note,NoteAdapter.NoteHolder> {

  //  private List<Note> notes= new ArrayList<>();

    public static final  DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.equals(newItem);
        }
    };

    public NoteAdapter() {

        super(DIFF_CALLBACK);
    }



    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item,viewGroup,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {

        Note note=getItem(i);
        noteHolder.tvTitle.setText(note.getTitle());
        noteHolder.tvPriority.setText(note.getPriority()+"");
        noteHolder.tvDescription.setText(note.getDescription());
    }



    public Note getNote(int position){
        return getItem(position);
    }



    class NoteHolder extends RecyclerView.ViewHolder{


        private TextView tvTitle,tvDescription,tvPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvPriority=itemView.findViewById(R.id.tv_priority);
            tvDescription=itemView.findViewById(R.id.tv_description);
        }
    }


    interface OnItemClickListener{
        void onClick();

    }
}
