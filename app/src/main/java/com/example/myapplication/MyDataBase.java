package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBase extends SQLiteOpenHelper {

    public Context context;
    public static final String DataBase_Name="Notes";
    public static final int DataBase_Version=1;

    public static final String Table_Name="My_Notes";
    public static final String Column_ID="id";
    public static final String Column_title="title";
    public static final String Column_text="text";
    public static final String Column_Date="Date";

    public MyDataBase(@Nullable Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Table_Name +
                " (" + Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Column_title + " TEXT, " +
                Column_text + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    void addNote(String title, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_title,title);
        cv.put(Column_text,text);
long result = db.insert(Table_Name,null,cv);
if (result==-1){
    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
}else {
    Toast.makeText(context, "Aded Succesfully", Toast.LENGTH_SHORT).show();
}

    }



    Cursor readAllData(){
        String query = "SELECT * FROM " + Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    void updateData(String row_id, String title, String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_title, title);
        cv.put(Column_text, author);

        long result = db.update(Table_Name, cv, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Table_Name, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
