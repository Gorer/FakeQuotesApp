package com.example.myfakequotesapp.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myfakequotesapp.Model.Quote;
import com.example.myfakequotesapp.Model.QuoteDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Quote.class},version = 2)
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
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuoteRoomDatabase.class, "fake_quotes_database")
                            //.allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
