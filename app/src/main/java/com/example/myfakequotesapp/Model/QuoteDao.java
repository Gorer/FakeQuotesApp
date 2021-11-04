package com.example.myfakequotesapp.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuoteDao {
    @Query("SELECT * FROM quote")
    LiveData<List<Quote>> getAllQuote();

    @Query("SELECT * FROM quote WHERE id == :quoteId")
    Quote getQuoteByID(int quoteId);

    @Insert
    void insertQuote(Quote quote);

    @Delete
    void DeleteQuote(Quote quote);

    @Update
    void updateQuote(Quote quote);
}
