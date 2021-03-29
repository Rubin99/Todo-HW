package com.example.todomvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todomvvm.ui.todo.TodoFragment;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TodoFragment.newInstance())
                    .commitNow();
        }
    }
}
