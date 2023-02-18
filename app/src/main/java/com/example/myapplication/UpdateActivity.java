package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input,Text_input;
    ImageView imageView_save, delete;

String id,title,text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        delete=findViewById(R.id.delete);
        title_input = findViewById(R.id.title_input2);
        Text_input = findViewById(R.id.Text_input2);
        imageView_save = findViewById(R.id.imageView_save2);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase myDB = new MyDataBase(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                text = Text_input.getText().toString().trim();
                myDB.updateData(id, title, text);
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDataBase myDB=new MyDataBase(UpdateActivity.this);
                myDB.deleteOneRow(id);
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("text")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            text = getIntent().getStringExtra("text");

            //Setting Intent Data
            title_input.setText(title);
            Text_input.setText(text);

            Log.d("stev", title+" "+text+" ");
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

//    void updateAdapter(ArrayList<ListItem> list) {
//        listMainLocal.clear();
//        listMainLocal.addAll(listItems);
//        notifyDataSetChanged();
//    }

}