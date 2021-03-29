package com.example.todomvvm.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoRoomDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();

    // singleton to avoid multiple instances of the DB
    private static volatile TodoRoomDatabase INSTANCE;

    static TodoRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null){
            synchronized ( (TodoRoomDatabase.class)) {
                if ( INSTANCE == null) {
                    // create DB
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoRoomDatabase.class, "todo_database")
                            .addCallback(sRoomDatabaseCallback) // insert test data
                            .build();
                }
            }

        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database with initial test data.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
//                  super.onCreate(db);

                    // If you want to keep the data through app restarts,
                    // comment out the following line.
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background with initial test data
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TodoDao mDao;

        PopulateDbAsync(TodoRoomDatabase db) {
            mDao = db.todoDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Todo todo = new Todo("Wake up!");
            todo.setDetail("either set 2 alarm clocks or none");
            mDao.insert(todo);

            Todo todo1 = new Todo("Drink coffee!");
            todo.setDetail("Use the liter mugs");
            mDao.insert(todo1);

            Todo todo2 = new Todo("Ponder the duality of existence!");
            todo.setDetail("and plant trees");
            mDao.insert(todo2);

            Todo todo3 = new Todo("make someone laugh");
            todo.setDetail("read a comic");
            mDao.insert(todo3);

            return null;
        }
    }


}
