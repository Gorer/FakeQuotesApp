package com.example.myfakequotesapp.Room;

import android.app.Application;
import android.widget.Toast;

public class Repository {
    //static QuoteRoomDatabase database;
    static RepositoryTasks database;
    static Application app;

    static public void initDataBase(Application application){
        app = application;
        if(database == null){
            database = new QuoteRepository(application);
        }
    }
    static public RepositoryTasks getDataBase(){
        if(database == null) {
            Toast.makeText(app,"No db", Toast.LENGTH_LONG).show();
        }
        return database;
    }
}
