package com.example.myfakequotesapp.Presentation.Repository.Room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myfakequotesapp.Domain.Model.Quote;
import com.example.myfakequotesapp.Presentation.Repository.Room.DAO.QuoteDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Quote.class},version = 1)
public abstract class QuoteRoomDatabase extends RoomDatabase {
    /*public abstract QuoteDao quoteDao();

    private static  QuoteRoomDatabase INSTANCE;

    public  static  QuoteRoomDatabase getInstance(Context context){

        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    QuoteRoomDatabase.class,"QuoteDB").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }*/

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
                            QuoteRoomDatabase.class, "fake_quote_2_database")//"word_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
