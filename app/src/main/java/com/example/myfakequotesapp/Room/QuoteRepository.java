package com.example.myfakequotesapp.Room;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfakequotesapp.Model.Quote;
import com.example.myfakequotesapp.Model.QuoteDao;

import java.util.List;

public class QuoteRepository implements RepositoryTasks {
    private QuoteDao mQuoteDao;
    private LiveData<List<Quote>> mAllQuotes = new MutableLiveData<>();

    public QuoteRepository(Application application) {
        QuoteRoomDatabase db = QuoteRoomDatabase.getInstance(application);
        // немного непонимаю, что мы сюда записываем
        mQuoteDao = db.quoteDao();
        mAllQuotes = mQuoteDao.getAllQuote();
    }

    public LiveData<List<Quote>> getAllQuotes() {
        return mAllQuotes;
    }

    @Override
    public <T extends Quote> void addQuote(T quote) {
        QuoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuoteDao.insertQuote(((Quote) quote));
        });
    }

    @Override
    public <T extends Quote> void deleteQuote(T quote) {
        QuoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuoteDao.DeleteQuote(((Quote) quote));
        });
    }
}
