package com.example.myfakequotesapp.ViewModel

import androidx.lifecycle.LiveData
import com.example.myfakequotesapp.Model.Quote
import androidx.lifecycle.ViewModel
import com.example.myfakequotesapp.Room.Repository

class QuoteListViewModel : ViewModel() {
    val getListFromViewModel: LiveData<List<Quote?>?>?
        get() = Repository.getDataBase?.getAllQuotes()

    fun deleteQuote(quote: Quote?) {
        Repository.getDataBase?.deleteQuote(quote)
    }
}