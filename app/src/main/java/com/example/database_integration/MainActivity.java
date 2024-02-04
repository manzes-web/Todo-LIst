package com.example.database_integration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addButton;

    Database myDB;
    ArrayList<String> todo_work, todo_id;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        addButton = findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        myDB = new Database(MainActivity.this);
        todo_id = new ArrayList<>();
        todo_work = new ArrayList<>();

        displayData();

        customAdapter = new CustomAdapter(MainActivity.this,this,todo_id,todo_work);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void displayData(){

        Cursor cursor = myDB.readDataFromDatabase();
        if (cursor.getCount() == 0){

            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();

        }else {

            while (cursor.moveToNext()){
                todo_id.add(cursor.getString(0));
                todo_work.add(cursor.getString(1));

            }
        }
    }
}