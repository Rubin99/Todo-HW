package com.example.todoassignment.ui.todo;

import com.example.todoassignment.database.Todo;

public interface ItemClickLitsener {
    void onItemClickListener(int itemId);
    void onItemDeleted(int itemId);
    void onUpdate(Todo todo);
}
