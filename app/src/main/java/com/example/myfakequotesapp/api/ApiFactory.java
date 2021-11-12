package com.example.myfakequotesapp.api;

import android.app.DownloadManager;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfakequotesapp.BuildConfig;
import com.example.myfakequotesapp.pojo.Example;
import com.example.myfakequotesapp.pojo.Result;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class ApiFactory {
    private static ApiFactory apiFactory;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://imdb-api.com/en/API/SearchTitle/k_w1rp6vvu/";

    private ApiFactory() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public LiveData<Example> getExample(String searchTitle) {
        MutableLiveData<Example> liveExamplesData = new MutableLiveData<>();
        //List
        apiFactory.getApiService().getExample(searchTitle).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveExamplesData.setValue(response.body());
                    Log.d(TAG, "INFO: " + response);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d(TAG, "ERROR: " + t.getMessage());
            }
        });
        return liveExamplesData;
    }

    public static ApiFactory getInstance() {
        if (apiFactory == null) {
            apiFactory = new ApiFactory();
        }
        return apiFactory;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
