package com.example.todomvvm.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomvvm.R;
import com.example.todomvvm.database.Todo;

import java.util.List;

import static androidx.lifecycle.ViewModelProviders.of;

public class TodoFragment extends Fragment {

    public static TodoFragment newInstance() {
        return new TodoFragment();
    }

    private TodoListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//      return inflater.inflate(R.layout.main_fragment, container, false);

        View view;
        view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        this.adapter = new TodoListAdapter(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // deprecated, mTodoViewModel = of(this).get(TodoViewModel.class);
        TodoViewModel mTodoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        // TODO: Use the ViewModel

        // Add an observer on the LiveData returned by getTodos.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mTodoViewModel.getTodos().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable final List<Todo> todos) {
                // Update the cached copy of the todos in the adapter.
                adapter.setTodos(todos);
            }
        });

    }

}
