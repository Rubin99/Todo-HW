package com.example.todoapphw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.todoapphw.data.Repository;
import com.example.todoapphw.data.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {


    public static final String EXTRA_TASK_ID ="extraTaskId";
    public static final String INSTANCE_TASK_ID = "instanceTaskId";
    public static final int DEFAULT_TASK_ID = -1;

    private static final String TAG = AddTaskActivity.class.getSimpleName();

    private EditText titleEditTExt;
    private EditText descEditText;
    private EditText setDate;
    RadioGroup mRadioGroup;
    private Button addButton;
    private Repository repository;

    private int mTaskId = DEFAULT_TASK_ID;

    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;

    Calendar c;
    DatePickerDialog dpd;

    AddEditTaskViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        /*titleEditTExt = findViewById(R.id.title_entry);
        descEditText = findViewById(R.id.desc_entry);
        setDate = findViewById(R.id.date_edit);
        ImageView dateButton = (ImageView)findViewById(R.id.imgDate);
        addButton = findViewById(R.id.submit_btn);*/
        repository = Repository.getRepository(this.getApplication());

        initViews();

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            addButton.setText(R.string.update_button);

            if (mTaskId == DEFAULT_TASK_ID) {
                // populate the UI

                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);
                AddEditTaskViewModelFactory factory = new AddEditTaskViewModelFactory(getApplication(), mTaskId);
                viewModel = ViewModelProviders.of(this, factory).get(AddEditTaskViewModel.class);

                viewModel.getTask().observe(this, new Observer<List<Task>>() {
                    @Override
                    public void onChanged(List<Task> task) {
                        viewModel.getTask().removeObserver(this);
                        populateUI((Task) task); /// .............................
                    }
                });

            }
        }else{
            AddEditTaskViewModelFactory factory = new AddEditTaskViewModelFactory(getApplication(), mTaskId);
            viewModel = ViewModelProviders.of(this, factory).get(AddEditTaskViewModel.class);
        }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, mTaskId);
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        titleEditTExt = findViewById(R.id.title_entry);
        descEditText = findViewById(R.id.desc_entry);
        setDate = findViewById(R.id.date_edit);
        ImageView dateButton = (ImageView)findViewById(R.id.imgDate);
        mRadioGroup = findViewById(R.id.radioGroup);
        addButton = findViewById(R.id.submit_btn);
    }

    private void populateUI(Task task) {
        if(task == null){
            return;
        }
        titleEditTExt.setText(task.getTitle());
        setPriorityInViews(task.getPriority());

    }

    public void onSubmitButtonClicked() {
        // Not yet implemented
        String title = titleEditTExt.getText().toString();
        String description = descEditText.getText().toString();
        int priority = getPriorityFromViews();
        Date date = new Date();
        Task todo = new Task(title, description, priority, date);
        if(mTaskId == DEFAULT_TASK_ID)
            viewModel.insertTask(todo);
        else{
            todo.setId(mTaskId);
            viewModel.updateTask(todo);

        }
        finish();

    }

    private int getPriorityFromViews() {
        int priority = 1;
        int checkedId = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();

        switch (checkedId) {
            case R.id.radButton1:
                priority = PRIORITY_HIGH;
                break;
            case R.id.radButton2:
                priority = PRIORITY_MEDIUM;
                break;
            case R.id.radButton3:
                priority = PRIORITY_LOW;
                break;
        }
        return priority;
    }

    private void setPriorityInViews(int priority) {
        switch (priority) {
            case PRIORITY_HIGH:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.radButton1);
                break;
            case PRIORITY_MEDIUM:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.radButton2);
                break;
            case PRIORITY_LOW:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.radButton3);
        }

    }




    public void onSetDate(View view) {
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        dpd = new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int sYear, int sMonth, int sDay) {
                setDate.setText(sDay + "/" + sMonth + "/" + sYear);
            }
        }, year, month, day);
        dpd.show();
    }
}