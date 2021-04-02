package com.example.todoassignment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.todoassignment.database.Todo;
import com.example.todoassignment.database.TodoRepository;
import com.example.todoassignment.ui.todo.TodoFragment;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass for updating Tasks.
 */
public class UpdateFragment extends Fragment {

    //parameters
    private static final String TAG = TodoActivity.class.getSimpleName();
    private EditText titleEditTExt;
    private EditText descEditText;
    private EditText setDate;
    RadioGroup mRadioGroup;
    private Button updateButton;
    private TodoRepository repository;
    private ImageView imgDate;
    private EditText dateEditText;
    private DatePickerDialog datePickerDialog;

    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;

    private Todo todoUpdate;

    public static UpdateFragment newInstance() {
        return new UpdateFragment();
    }

    public UpdateFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //For update
        Bundle bundle=this.getArguments();
        assert bundle != null;
        todoUpdate = (Todo) bundle.getSerializable("todo");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view;
        // Inflating the layout
        view = inflater.inflate(R.layout.fragment_update, container, false);

        titleEditTExt = (EditText) view.findViewById(R.id.title_entry);
        descEditText = (EditText) view.findViewById(R.id.desc_entry);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        imgDate = (ImageView) view.findViewById(R.id.imgDate);
        dateEditText = (EditText) view.findViewById(R.id.date_edit);
        updateButton = (Button) view.findViewById(R.id.update_btn);

        repository = new TodoRepository(getActivity().getApplication());

// -------------------------------------------------------- Update Task --------------------------------------------------------------
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditTExt.getText().toString();
                String desc = descEditText.getText().toString();
                int priority = getPriorityFromViews();
                String date = dateEditText.getText().toString();
                todoUpdate.setTitle(title.trim());
                todoUpdate.setDescription(desc);
                todoUpdate.setPriority(priority);
                todoUpdate.setDate(date);
                repository.update(todoUpdate);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, TodoFragment.newInstance())
                        .commitNow();
            }
//------------------------------------------------------------------------------------------------------------------------------------

            //Method to get priority
            private int getPriorityFromViews() {
                int priority = 1;
                int checkedId = ((RadioGroup) view.findViewById(R.id.radioGroup)).getCheckedRadioButtonId(); //Made view final

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
            //Method to set priority
            private void setPriorityInViews(int priority) {
                switch (priority) {
                    case PRIORITY_HIGH:
                        ((RadioGroup) view.findViewById(R.id.radioGroup)).check(R.id.radButton1);
                        break;
                    case PRIORITY_MEDIUM:
                        ((RadioGroup) view.findViewById(R.id.radioGroup)).check(R.id.radButton2);
                        break;
                    case PRIORITY_LOW:
                        ((RadioGroup) view.findViewById(R.id.radioGroup)).check(R.id.radButton3);
                }

            }
        });

        // View and set Calendar on click
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        dateEditText.setText(day + "/" + (month) + "/" + year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        return view;
    }
}