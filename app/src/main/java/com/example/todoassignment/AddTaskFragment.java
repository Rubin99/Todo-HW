package com.example.todoassignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.todoassignment.database.Todo;
import com.example.todoassignment.database.TodoRepository;
import com.example.todoassignment.ui.todo.TodoListAdapter;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = AddTaskActivity.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText titleEditTExt;
    private EditText descEditText;
    private EditText setDate;
    RadioGroup mRadioGroup;
    private Button submitButton;
    private TodoRepository repository;

    // TODO: Rename and change types and number of parameters
    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
    }
    public AddTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_add_task, container, false);

        titleEditTExt = (EditText) view.findViewById(R.id.title_entry);
        descEditText = (EditText) view.findViewById(R.id.desc_entry);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        submitButton = (Button) view.findViewById(R.id.submit_btn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditTExt.getText().toString();
                String desc = descEditText.getText().toString();
                Todo todo = new Todo(title, desc, 1);
                repository.insert(todo);
                //finish();
            }
        });



        return view;
    }
}