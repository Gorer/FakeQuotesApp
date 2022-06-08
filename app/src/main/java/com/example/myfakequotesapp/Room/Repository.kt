package com.example.myfakequotesapp.Room

import android.widget.Toast
import android.app.Application

object Repository {
    //static QuoteRoomDatabase database;
    var database: RepositoryTasks? = null
    var app: Application? = null
    fun initDataBase(application: Application) {
        app = application
        if (database == null) {
            database = QuoteRepository(application)
        }
    }

    val getDataBase: RepositoryTasks?
        get() {
            if (database == null) {
                Toast.makeText(app, "No db", Toast.LENGTH_LONG).show()
            }
            return database
        }
}