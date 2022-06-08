package com.example.myfakequotesapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfakequotesapp.Model.Quote
import com.example.myfakequotesapp.Model.QuoteDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [Quote::class], version = 2)
abstract class QuoteRoomDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao?

    companion object {
        @Volatile
        private var INSTANCE: QuoteRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getInstance(context: Context): QuoteRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(QuoteRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            QuoteRoomDatabase::class.java, "fake_quotes_database"
                        ) //.allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}