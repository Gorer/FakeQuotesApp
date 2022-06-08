package com.example.myfakequotesapp.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuoteDao {
    @get:Query("SELECT * FROM quote")
    val allQuote: LiveData<List<Quote?>?>?

    @Query("SELECT * FROM quote WHERE id == :quoteId")
    fun getQuoteByID(quoteId: Int): Quote?

    @Insert
    fun insertQuote(quote: Quote?)

    @Delete
    fun DeleteQuote(quote: Quote?)

    @Update
    fun updateQuote(quote: Quote?)
}