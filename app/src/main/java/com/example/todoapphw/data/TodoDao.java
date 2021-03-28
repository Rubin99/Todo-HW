package com.example.todoapphw.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TodoDao {

    @Query("Select * from Tasks order by priority")
    public LiveData<List<Task>> getAllTasks();

    @Query("Select * from Tasks where id =:taskId")
    public LiveData<List<Task>> loadtaskById(int taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Task task);

    @Query("delete from Tasks")
    void deleteAll();

    @Delete
    void  delete(Task task);

    @Update
    void  update(Task task);
}
