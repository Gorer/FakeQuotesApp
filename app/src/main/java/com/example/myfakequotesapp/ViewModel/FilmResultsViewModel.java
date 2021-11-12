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

    //List<Result> films;
    /*public List<Result> getResultsList(String searchTitle) {
        try {
            return ApiFactory.getInstance().getApiService().getExample(searchTitle).execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
    /*public List<Result> getResultsList(String searchTitle) {
        films = new ArrayList<>();
        ApiFactory.getInstance().getApiService().getExample(searchTitle).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                films = response.body().getResults();
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d(TAG, "ERROR: " + t.getMessage());
            }
        });
        return films;
    }*/
}
