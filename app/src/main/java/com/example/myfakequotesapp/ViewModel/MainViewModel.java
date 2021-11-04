package com.example.myfakequotesapp.ViewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfakequotesapp.Model.Quote;
import com.example.myfakequotesapp.Room.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends ViewModel {
    private LiveData<List<Quote>> quotes;

    public LiveData<List<Quote>> getQuotes() {
        return Repository.getDataBase().getAllQuotes();
    }

    public Quote getQuote(int id) {
        return Repository.getDataBase().getQuoteByID(id);
    }

    public Quote getQuoteById(int id) {
        try {
            return new GetQuoteTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetQuoteTask extends AsyncTask<Integer, Void, Quote> {

        @Override
        protected Quote doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0) {
                return Repository.getDataBase().getQuoteByID(integers[0]);
            }
            return null;
        }
    }
}






















