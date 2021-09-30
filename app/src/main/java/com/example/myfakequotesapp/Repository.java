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
            database=QuoteRoomDatabase.getInstance(application);


        }
    }
    static  public QuoteRoomDatabase getDataBase(){
        if(database==null){
            Toast.makeText(app,"No db",Toast.LENGTH_LONG).show();
        }
        return database;
    }
}
