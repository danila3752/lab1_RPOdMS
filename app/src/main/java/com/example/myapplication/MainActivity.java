package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView List_elements;
    MyDataBase myDB;
    ImageView delete_button;
    CustomAdapter customAdapter;
    ArrayList<String> notes;
    SearchView search_home;
    ArrayList<String>  note_id,note_title,note_text;



    FloatingActionButton add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_home=findViewById(R.id.search_home);
        List_elements=findViewById(R.id.List_elements);
        add_button=findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        search_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        myDB = new MyDataBase(MainActivity.this);
        note_id=new ArrayList<>();
        note_title=new ArrayList<>();
        note_text=new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, note_id, note_title, note_text);
        List_elements.setAdapter(customAdapter);
        List_elements.setLayoutManager(new LinearLayoutManager(MainActivity.this));

         notes = new ArrayList<>();
         notes.add(note_title.toString());
         notes.add(note_text.toString());

    }

    private void filter(String newText) {
        List<String> filteredlist= new ArrayList<>();
        for (int i=0;i<notes.size();i++)
       if(notes.toString().contains(newText))
        customAdapter.fillterList(filteredlist);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                note_id.add(cursor.getString(0));
                note_title.add(cursor.getString(1));
                note_text.add(cursor.getString(2));
            }

        }
    }



}