package com.example.myfakequotesapp.Room;

import androidx.lifecycle.LiveData;

import com.example.myfakequotesapp.Model.Quote;

import java.util.List;

public interface RepositoryTasks {
    <T extends Quote> LiveData<List<T>> getAllQuotes();
    <T extends Quote> Quote getQuoteByID(int quoteId);
    <T extends Quote> void addQuote(T Quote);
    <T extends Quote> void deleteQuote(T Quote);
    <T extends Quote> void updateQuote(T Quote);
}
