package com.example.myfakequotesapp.Presentation.Repository.Room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myfakequotesapp.Domain.Model.Quote;

import java.util.List;

@Dao
public interface QuoteDao {
    @Query("SELECT * FROM quote")
    List<Quote> getAllQuote();

    @Insert
    void insertQuote(Quote quote);

    @Delete
    void DeleteQuote(Quote quote);
}
