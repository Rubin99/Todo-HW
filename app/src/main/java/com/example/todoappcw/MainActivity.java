package com.example.todoappcw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.todoappcw.data.AppDatabase;
import com.example.todoappcw.data.Repository;
import com.example.todoappcw.data.Task;
import com.example.todoappcw.data.TodoDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private  TaskAdapter adapter;
    private MainViewModel viewModel;
    private FloatingActionButton addButton;
    List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.task_list);
        adapter = new TaskAdapter();

//        repository = Repository.getRepository(this.getApplication());
//        List<Task> tasks = repository.getAllTask();
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if (tasks != null)
                adapter.setData(tasks);
            }
        });
        //adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);

        addButton = findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

    }
 //   @Override
  //  protected  void onStart() {
   //     super.onStart();
    //    tasks = viewModel.getAllTasks();
     //   adapter.setData(tasks);
   // }
}