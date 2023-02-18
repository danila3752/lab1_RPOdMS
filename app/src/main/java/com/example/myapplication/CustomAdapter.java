package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    CardView cardView;
    Context context;
    private Activity activity;
    private ArrayList notes;


    CustomAdapter(Activity activity, Context context, ArrayList notes) {
        this.activity = activity;
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
       View view= inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ListItem item = (ListItem) notes.get(position);
        holder.note_id_txt.setText(String.valueOf(item.id));
        holder.note_title_txt.setText(String.valueOf(item.title));
        holder.note_text_txt.setText(String.valueOf(item.text));
        //holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.containerNouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UpdateActivity.class);
                ListItem item = (ListItem) notes.get(position);
                intent.putExtra("id", String.valueOf(item.id));
                intent.putExtra("title", String.valueOf(item.title));
                intent.putExtra("text", String.valueOf(item.text));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void fillterList(ArrayList<ListItem> filterList){
        notes = filterList;
        //notes.addAll(filterList);
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView note_id_txt, note_title_txt, note_text_txt;
        CardView containerNouts;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title_txt = itemView.findViewById(R.id.view_title);
            note_text_txt = itemView.findViewById(R.id.notes);
            containerNouts= itemView.findViewById(R.id.containerNouts);
            note_id_txt= itemView.findViewById(R.id.note_id_txt);
        }
    }
}
