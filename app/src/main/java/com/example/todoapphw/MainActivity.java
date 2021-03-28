package com.example.todoapphw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.todoapphw.data.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.ItemClickListener {

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new TaskAdapter(this, this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

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



        addButton = findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClickListener(int itemId) {

    }
    //   @Override
  //  protected  void onStart() {
   //     super.onStart();
    //    tasks = viewModel.getAllTasks();
     //   adapter.setData(tasks);
   // }



}