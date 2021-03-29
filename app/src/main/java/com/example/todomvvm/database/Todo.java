package com.example.todomvvm.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * A basic class representing a one-column todo_database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 * See the documentation for the full set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "todo_table")
public class Todo {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;
    private String mDetail;
    
    public Todo(@NonNull String title) {
        this.mTitle = title;
    }
    public String getTitle() {
        return this.mTitle;
    }

    String getDetail() {
        return this.mDetail;
    }
    void setDetail(String mDetail) {
        this.mDetail = mDetail;
    }

}
