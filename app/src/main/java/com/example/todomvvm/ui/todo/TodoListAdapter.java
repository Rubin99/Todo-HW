package com.example.todomvvm.ui.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomvvm.R;
import com.example.todomvvm.database.Todo;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoViewHolder> {

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private final TextView todoItemView;

        private TodoViewHolder(View itemView) {
            super(itemView);
            todoItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Todo> mTodos; // Cached copy of todos

    TodoListAdapter(TodoFragment context) {
        mInflater = LayoutInflater.from(context.getContext());
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        if (mTodos != null) {
            Todo current = mTodos.get(position);
            holder.todoItemView.setText(current.getTitle());
        } else {
            // Covers the case of data not being ready yet.
            holder.todoItemView.setText(R.string.no_todo);
        }
    }

    void setTodos(List<Todo> todos){
        mTodos = todos;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mTodos has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTodos != null)
            return mTodos.size();
        else return 0;
    }
}