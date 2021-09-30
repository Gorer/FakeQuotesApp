package com.example.myfakequotesapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuoteListViewModel extends ViewModel {
    public LiveData<List<Quote>> getListFromViewModel(){

        //Log.d(TAG,"Переменная живойдаты in ListViewModel "+Repository.getDataBase().quoteDao().getAllUser().getValue());


        return Repository.getDataBase().quoteDao().getAllQuote();

    }
    public void deleteQuote(Quote product) {

        Repository.getDataBase().quoteDao().DeleteQuote(product);
    }
}
