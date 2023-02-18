package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddActivity extends AppCompatActivity {
CustomAdapter customAdapter;    EditText title_input,Text_input;
    ImageView imageView_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        Text_input = findViewById(R.id.Text_input);
        imageView_save = findViewById(R.id.imageView_save);
        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase myDB = new MyDataBase(AddActivity.this);
                myDB.addNote(title_input.getText().toString(),Text_input.getText().toString());
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}