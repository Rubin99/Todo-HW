package com.example.todoapphw;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoapphw.data.AppDatabase;
import com.example.todoapphw.data.Repository;
import com.example.todoapphw.data.Task;

import java.util.List;

public class AddEditTaskViewModel extends AndroidViewModel {

    Repository repository;
    LiveData<List<Task>> task;

    public AddEditTaskViewModel(Application application, int taskId){
        super(application);
        AppDatabase database = AppDatabase.getDatabase(application);
        repository = new Repository(application);
        if(taskId != -1)
            task = repository.getTaskById(taskId);
    }


    public LiveData<List<Task>> getTask(){
        return task;
    }

    public void insertTask(Task task){
        repository.insert(task);
    }

    public void updateTask(Task task){
        repository.insert(task);
    }

}
