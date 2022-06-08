package com.example.myfakequotesapp.Room

import androidx.lifecycle.LiveData
import com.example.myfakequotesapp.Model.Quote
import org.jetbrains.annotations.NotNull

interface RepositoryTasks {
    @NotNull
    fun <T : Quote?> getAllQuotes(): LiveData<List<T?>?>?
    fun <T : Quote?> getQuoteByID(quoteId: Int): Quote?
    fun <T : Quote?> addQuote(Quote: T)
    fun <T : Quote?> deleteQuote(Quote: T)
    fun <T : Quote?> updateQuote(Quote: T?)
    //fun updateQuote(quote: Quote?)
}