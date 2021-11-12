package com.example.myfakequotesapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfakequotesapp.api.ApiFactory;
import com.example.myfakequotesapp.pojo.Example;
import com.example.myfakequotesapp.pojo.Result;

import java.util.List;

public class FilmResultsViewModel extends ViewModel {

    public LiveData<Example> getExample(String searchTitle) {
        return ApiFactory.getInstance().getExample(searchTitle);
    }
}
