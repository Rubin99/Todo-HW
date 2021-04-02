package com.example.todoassignment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Room uses this DAO where you map a Java method call to an SQL query.
 *
 * When you are using complex data types, such as Date, you have to also supply type converters.
 * To keep this example basic, no types that require type converters are used.
 * See the documentation at
 * https://developer.android.com/topic/libraries/architecture/room.html#type-converters
 */

@Dao
public interface TodoDao {

    @Query("Select * from todo_table order by priority")
    public LiveData<List<Todo>> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Todo todo);

    @Query("delete from todo_table")
    void deleteAll();

    @Delete
    public void  delete(Todo todo);

    @Update
    void  update(Todo todo);

}
