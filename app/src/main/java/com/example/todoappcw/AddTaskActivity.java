package com.example.todoappcw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todoappcw.data.Repository;
import com.example.todoappcw.data.Task;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    private EditText titleEditTExt;
    private EditText descEditText;
    private Button addButton;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        titleEditTExt = findViewById(R.id.title_entry);
        descEditText = findViewById(R.id.desc_entry);
        addButton = findViewById(R.id.submit_btn);
        repository = Repository.getRepository(this.getApplication());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditTExt.getText().toString();
                String desc = descEditText.getText().toString();
                Task task = new Task(title, desc, 1, new Date());
                repository.insert(task);
                finish();
            }
        });
    }

}