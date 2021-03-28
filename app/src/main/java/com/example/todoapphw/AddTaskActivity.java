package com.example.todoapphw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class AddTaskActivity extends AppCompatActivity {
    private EditText titleEditTExt;
    private EditText descEditText;
    private EditText setDate;
    private Button addButton;
    private Repository repository;

    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;

    Calendar c;
    DatePickerDialog dpd;

    RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        titleEditTExt = findViewById(R.id.title_entry);
        descEditText = findViewById(R.id.desc_entry);
        setDate = findViewById(R.id.date_edit);
        ImageView dateButton = (ImageView)findViewById(R.id.imgDate);
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