package com.example.todoassignment.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

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

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private  String title;
    private String description;
    private int priority;
   /* @ColumnInfo(name = "created date")
    private Date createdDate;*/

    public Todo(String title, String description, int priority) { //, Date createdDate
        this.title = title;
        this.description = description;
        this.priority = priority;
        //this.createdDate = createdDate;
    }

    @Ignore
    public Todo(int id, String title, String description, int priority, Date createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        //this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /*public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }*/
}