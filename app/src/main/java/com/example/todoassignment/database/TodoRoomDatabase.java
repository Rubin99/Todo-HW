package com.example.todoassignment.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class TodoRoomDatabase extends RoomDatabase{
    private static TodoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract TodoDao todoDao();

    public static TodoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TodoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoRoomDatabase.class, "todo.db")
                            .addCallback(CALLBACK)
                            //.allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private  static  RoomDatabase.Callback CALLBACK = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            TodoDao dao = INSTANCE.todoDao();

        }
        @Override
        public void  onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}
