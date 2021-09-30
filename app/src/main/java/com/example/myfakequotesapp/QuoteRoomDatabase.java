package com.example.myfakequotesapp;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Quote.class},version = 1)
public abstract class QuoteRoomDatabase extends RoomDatabase {
    public abstract QuoteDao quoteDao();

    private static volatile QuoteRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static QuoteRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Узнать про context, getApplication(), getApplicationContext()
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuoteRoomDatabase.class, "fake_quote_2_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
