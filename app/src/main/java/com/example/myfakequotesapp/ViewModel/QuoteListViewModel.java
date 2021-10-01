package com.example.myfakequotesapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfakequotesapp.Model.Quote;
import com.example.myfakequotesapp.Room.Repository;

import java.util.List;

public class QuoteListViewModel extends ViewModel {
    public LiveData<List<Quote>> getListFromViewModel(){
        return Repository.getDataBase().getAllQuotes();

    }
    public void deleteQuote(Quote quote) {
        Repository.getDataBase().deleteQuote(quote);
    }
}
