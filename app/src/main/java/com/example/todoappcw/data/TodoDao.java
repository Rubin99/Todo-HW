package com.example.todoappcw.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoappcw.data.Task;

import java.util.List;
@Dao
public interface TodoDao {

    @Query("Select * from Tasks order by priority")
    public List<Task> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Task task);

    @Query("delete from Tasks")
    void deleteAll();

    @Delete
    void  delete(Task task);

    @Update
    void  update(Task task);
}
