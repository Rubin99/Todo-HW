package com.example.todoapphw;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoapphw.data.AppDatabase;
import com.example.todoapphw.data.Repository;
import com.example.todoapphw.data.Task;

public class AddEditTaskViewModel extends AndroidViewModel {

    Repository repository;
    LiveData<Task> task;

    AddEditTaskViewModel(Application application, int taskId){
        super(application);
        AppDatabase database = AppDatabase.getDatabase(application);
        repository = new Repository(database);
        if(taskId != -1)
            task = repository.getTaskById(taskId);
    }


    public LiveData<Task> getTask(){
        return task;
    }

    public void insertTask(Task task){
        repository.insertTask(task);
    }

    public void updateTask(Task task){
        repository.insertTask(task);
    }

}
