package com.example.database_integration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.security.interfaces.EdECKey;
import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {
    Button button1, button2;
    EditText editText;
    String todo_id,todo_work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.editTextText4);
        button1 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button);
        getIntentData();
        button1.setOnClickListener(view -> {
            String updatedTodoWork = editText.getText().toString().trim();
            Database myDB = new Database(UpdateActivity.this);
            myDB.UpdateTable(todo_id,updatedTodoWork);
           finish();
        });
        button2.setOnClickListener(view -> {
            ConfirmDialogueBox();
        });

    }
    void getIntentData(){
        if (getIntent().hasExtra("id")&& getIntent().hasExtra("todo_work")){
            todo_id = getIntent().getStringExtra("id");
            todo_work = getIntent().getStringExtra("todo_work");

            editText.setText(todo_work);

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();  // This will simulate the back button press
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    void ConfirmDialogueBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setMessage("Are you sure want to delete this ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database db = new Database(UpdateActivity.this);
                db.DeleteData(todo_id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}