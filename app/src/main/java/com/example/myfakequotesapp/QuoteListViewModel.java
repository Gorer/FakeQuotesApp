package com.example.myfakequotesapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuoteListViewModel extends ViewModel {
    public LiveData<List<Quote>> getListFromViewModel(){
        return Repository.getDataBase().quoteDao().getAllQuote();

    }
    public void deleteQuote(Quote quote) {
        Repository.getDataBase().quoteDao().DeleteQuote(quote);
    }
}
