package com.example.myfakequotesapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfakequotesapp.Model.Quote
import com.example.myfakequotesapp.Model.QuoteDaoNew
import java.util.concurrent.Executors


@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteRoomDatabaseNew : RoomDatabase() {
    abstract fun quoteDao(): QuoteDaoNew?

    companion object {
        @Volatile
        private var INSTANCE: QuoteRoomDatabaseNew? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getInstance(context: Context): QuoteRoomDatabaseNew? {
            if (INSTANCE == null) {
                synchronized(QuoteRoomDatabaseNew::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            QuoteRoomDatabaseNew::class.java, "fake_quotes_database"
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