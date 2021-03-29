package com.example.todomvvm.database;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.todomvvm.database.Todo;
import com.example.todomvvm.database.TodoDao;
import com.example.todomvvm.database.TodoRoomDatabase;

import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

public class TodoRepository {

    private TodoDao mTodoDao;
    private LiveData<List<Todo>> mTodos;

    // Note that in order to unit test the TodoRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    public TodoRepository(Application application) {
        TodoRoomDatabase db = TodoRoomDatabase.getDatabase(application);
        mTodoDao = db.todoDao();
        mTodos = mTodoDao.getTodos();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    public LiveData<List<Todo>> getTodos()  {
        return mTodos;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.

    public void insert (Todo todo) {
        new insertAsyncTask(mTodoDao).execute(todo);
    }

    private static class insertAsyncTask extends AsyncTask<Todo, Void, Void> {

        private TodoDao mAsyncTaskDao;

        insertAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground( final Todo... params) {

            mAsyncTaskDao.insert(params[0]);
            return null;
        }

    }

}
