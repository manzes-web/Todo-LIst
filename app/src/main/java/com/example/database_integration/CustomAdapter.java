package com.example.database_integration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final Context context;
    Activity activity;
    private final ArrayList todo_id, todo_work;
    CustomAdapter(Activity activity, Context context, ArrayList todo_id, ArrayList todo_work){
        this.context =context;
        this.todo_id = todo_id;
        this.todo_work =todo_work;
        this.activity =activity;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rows_recycler,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
//        holder.todo_id.setText(String.valueOf(todo_id.get(position)));
        holder.todo_work.setText(String.valueOf(todo_work.get(position)));
        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id",String.valueOf(todo_id.get(position)));
            intent.putExtra("todo_work", String.valueOf(todo_work.get(position)));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return todo_work.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView todo_id, todo_work;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.rows_recycler);
            todo_id = itemView.findViewById(R.id.todo_id);
            todo_work = itemView.findViewById(R.id.todo_work);
        }
    }
}
