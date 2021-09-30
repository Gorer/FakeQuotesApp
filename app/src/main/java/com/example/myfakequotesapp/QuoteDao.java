package com.example.myfakequotesapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuoteDao {
    @Query("SELECT * FROM quote")
    LiveData<List<Quote>> getAllQuote();

    @Insert
    void insertQuote(Quote quote);

    @Delete
    void DeleteQuote(Quote quote);
}
