package com.example.todoappcw.data;

import android.app.Application;

import java.util.List;

public class Repository {

    static Repository INSTANCE;
    AppDatabase db;
    TodoDao dao;

    private Repository(Application application) {
        db = AppDatabase.getDatabase(application);
        dao = db.todoDao();
    }

    public static Repository getRepository(Application application){
        if (INSTANCE == null){
            INSTANCE = new Repository(application);
        }
        return INSTANCE;
    }

    //Methods from TodoDao

    public List<Task> getAllTask(){
        return dao.getAllTasks();
    }

    public void deleteAll(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }

    public void update(Task task){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.update(task);
            }
        });
    }

    public void insert(Task task){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(task);
            }
        });
    }

}
