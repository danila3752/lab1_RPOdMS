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
    private ArrayList note_id, note_title, note_text;


    CustomAdapter(Activity activity, Context context, ArrayList note_id, ArrayList note_title, ArrayList note_text) {
        this.activity = activity;
        this.context = context;
        this.note_id = note_id;
        this.note_title = note_title;
        this.note_text = note_text;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder,  final int position) {
        holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.note_title_txt.setText(String.valueOf(note_title.get(position)));
        holder.note_text_txt.setText(String.valueOf(note_text.get(position)));
        holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.containerNouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(note_id.get(position)));
                intent.putExtra("title", String.valueOf(note_title.get(position)));
                intent.putExtra("text", String.valueOf(note_text.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    List<String> list;

    @Override
    public int getItemCount() {
        return note_id.size();
    }

    public void fillterList(List<String> filterList){
        list= filterList;
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
