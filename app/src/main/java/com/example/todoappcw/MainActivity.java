package com.example.todoappcw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.todoappcw.data.AppDatabase;
import com.example.todoappcw.data.Repository;
import com.example.todoappcw.data.Task;
import com.example.todoappcw.data.TodoDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private  TaskAdapter adapter;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.task_list);

        repository = Repository.getRepository(this.getApplication());
        List<Task> tasks = repository.getAllTask();
        adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);

    }
}