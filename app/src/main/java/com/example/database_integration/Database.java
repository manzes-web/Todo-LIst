package com.example.database_integration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TodoList.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_records";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TODO = "todo_work";
    private Context context;
    public Database(@Nullable  Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = " CREATE TABLE " + TABLE_NAME +
                            "( "+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                            COLUMN_TODO + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    void addBook(String todo_work){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(COLUMN_TODO,todo_work);
        Long result = db.insert(TABLE_NAME,null,value);

        if (result != -1){

            Toast.makeText(context, "successfully added", Toast.LENGTH_SHORT).show();

        }else {

            Toast.makeText(context, "Failed to insert", Toast.LENGTH_SHORT).show();

        }
    }
    Cursor readDataFromDatabase(){

        String query = "SELECT * FROM "+ TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if (db != null){

            cursor = db.rawQuery(query,null);

        }

        return cursor;

    }

    void UpdateTable(String row_id,String todo_work){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COLUMN_TODO, todo_work);
        long result = db.update(TABLE_NAME, value ,"_id=?" , new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
        }
    }
    void DeleteData(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
        }

    }
}
