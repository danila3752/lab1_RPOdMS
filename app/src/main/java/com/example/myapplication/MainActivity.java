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
    SearchView search_home;
    ArrayList<ListItem>  notes;



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
//        note_id=new ArrayList<>();
//        note_title=new ArrayList<>();
//        note_text=new ArrayList<>();
        notes = new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, notes);
        List_elements.setAdapter(customAdapter);
        List_elements.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    private void filter(String newText) {
        Cursor cursor = myDB.readAllData(newText);
        notes.clear();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                ListItem item = new ListItem();
                item.id = cursor.getInt(0);
                item.title = cursor.getString(1);
                item.text = cursor.getString(2);
                notes.add(item);
            }

        }
        cursor.close();
        customAdapter.fillterList(notes);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData("");
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                ListItem item = new ListItem();
                item.id = cursor.getInt(0);
                item.title = cursor.getString(1);
                item.text = cursor.getString(2);
                notes.add(item);
            }

        }
        cursor.close();
    }



}