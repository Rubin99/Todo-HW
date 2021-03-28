package com.example.todoapphw;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todoapphw.AddEditTaskViewModel;

public class AddEditTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    Application application;
    int taskId;

    public  AddEditTaskViewModelFactory(Application application, int taskId){
        this.application = application;
        this.taskId = taskId;
    }

    @NonNull

    public <T extends ViewModel> T creat(@NonNull Class<T> modelClass){
        return (T) new AddEditTaskViewModel(application, taskId);
    }
}
