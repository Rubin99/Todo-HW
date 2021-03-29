package com.example.todoassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoassignment.ui.todo.TodoFragment;

public class AddTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AddTaskFragment.newInstance())
                    .commitNow();
        }
    }
}
