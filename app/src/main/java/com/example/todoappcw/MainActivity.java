package com.example.todoappcw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.todoappcw.data.AppDatabase;
import com.example.todoappcw.data.Task;
import com.example.todoappcw.data.TodoDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getDatabase(this);
        TodoDao todoDao = db.todoDao();
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Task> tasks = todoDao.getAllTasks();
                Log.d(TAG, ""+tasks.size());
            }
        });

    }
}