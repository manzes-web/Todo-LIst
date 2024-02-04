package com.example.database_integration;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AddActivity extends AppCompatActivity {
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //assign the id of toolbar to toolbar2 variable
        Toolbar toolbar2 = findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar2);
        //onclick in the arrow goes to the homepage of the app
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        button = findViewById(R.id.button2);
        editText = findViewById(R.id.editTextText3);

        button.setOnClickListener(view -> {

            String todoText = editText.getText().toString().trim();

            if (!todoText.isEmpty()) {

                Database myDb = new Database(AddActivity.this);
                myDb.addBook(todoText);

            }else {

                Toast.makeText(this, "please add some todo work", Toast.LENGTH_SHORT).show();

            }
            Intent intent = new Intent(AddActivity.this,MainActivity.class);
            startActivity(intent);

        });
    }
}