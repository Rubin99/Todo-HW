package com.example.todoassignment.ui.todo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoassignment.AddTaskFragment;
import com.example.todoassignment.R;
import com.example.todoassignment.TodoActivity;
import com.example.todoassignment.UpdateFragment;
import com.example.todoassignment.database.Todo;
import com.example.todoassignment.database.TodoRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static androidx.lifecycle.ViewModelProviders.of;

public class TodoFragment extends Fragment{

    public static TodoFragment newInstance() {
        return new TodoFragment();
    }

    public TodoFragment() {
        // Required empty public constructor
    }

    private FloatingActionButton fab;

    private TodoListAdapter adapter;

    private EditText titleEditTExt;
    private EditText descEditText;
    private EditText setDate;
    RadioGroup mRadioGroup;
    private Button submitButton;
    private TodoRepository repository;
    private TextView titleView;

    private List<Todo> mTaskEntries;

     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ----------------------------------------------------- MENU ------------------------------------------------------------------
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.first_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete_all:  {
                repository.deleteAll();
                Toast.makeText(getContext(), "Deleted All", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, TodoFragment.newInstance())
                        .commitNow();

                return true;

            }
            case R.id.share: {
                String txt = "Git, Assignment, To-do App";
                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        .from(getActivity())
                        .setType(mimeType)
                        .setChooserTitle("Share this text with: ")
                        .setText(txt)
                        .startChooser();

            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    // ----------------------------------------------------------------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        titleView = view.findViewById(R.id.title_tv);
        this.adapter = new TodoListAdapter(getContext(), new TodoListAdapter.TaskCallBack() {
            @Override
            public void onItemDeleted(int id) {

            }

            @Override
            public void onUpdate(Todo todo) {
                ((TodoActivity)getActivity()).moveToUpdate(todo);
            }
        });

        //For Menu
        setHasOptionsMenu(true);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fab = view.findViewById(R.id.add_btn);

        //Divides a line between each View
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        // -------------------------------------- For Delete on Swipe ----------------------------------------------------------------

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<Todo> todoList = adapter.mTodos;
                TodoViewModel.delete(todoList.get(position));

                Toast toast = Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT);
                toast.show();
            }

        }).attachToRecyclerView(recyclerView);

        //----------------------------------------------------------------------------------------------------------------------------



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // deprecated, mTodoViewModel = of(this).get(TodoViewModel.class);
        TodoViewModel mTodoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);


        mTodoViewModel.getTodos().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable final List<Todo> todos) {
                // Update the cached copy of the todos in the adapter.
                adapter.setTodos(todos);
            }
        });

        // ------------------------------------------------Switches to AddTaskFragment-------------------------------------------------------
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Add Task", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, AddTaskFragment.newInstance())
                        .addToBackStack(null)
                        .commit();

            }

        });

        // ------------------------------------------------------------------------------------------------------------------------------------

        repository = new TodoRepository(getActivity().getApplication());

    }

}
