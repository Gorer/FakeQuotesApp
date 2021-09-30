package com.example.myfakequotesapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class Repository {
    static QuoteRoomDatabase database;
    static Application app;

    static public void initDataBase(Application application){
        app=application;
        if(database==null){
            Log.d(TAG,"Переменная database "+ QuoteRoomDatabase.getInstance(application));
            database=QuoteRoomDatabase.getInstance(application);


        }
    }
    static  public QuoteRoomDatabase getDataBase(){
        if(database==null){
            Toast.makeText(app,"Oh,Shiiiit",Toast.LENGTH_LONG).show();
        }
        return database;
    }

    /*static RepositoryTasks repository;

    static public void init(Application application) {
        if (repository == null) {
            repository = new QuoteRepository(application);
        }
    }

    static public RepositoryTasks getRepository() {
        if (repository == null) {
            return null;
        }
        return repository;
    }*/
}
