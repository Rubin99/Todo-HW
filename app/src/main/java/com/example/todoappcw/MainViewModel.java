package com.example.todoappcw;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoappcw.data.Repository;
import com.example.todoappcw.data.Task;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    Repository repository;
    LiveData<List<Task>> tasks;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
        tasks = repository.getAllTask();
    }

    public LiveData<List<Task>> getAllTasks(){
        return tasks;
    }

}
