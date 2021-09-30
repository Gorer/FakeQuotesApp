package com.example.myfakequotesapp.Presentation.Repository;

import androidx.lifecycle.LiveData;

import com.example.myfakequotesapp.Domain.Model.Quote;

import java.util.List;

public interface RepositoryTasks {
    <T extends Quote> LiveData<List<T>> getAllQuotes();
    <T extends Quote> void addQuote(T Quote);
    <T extends Quote> void deleteQuote(T Quote);
}
